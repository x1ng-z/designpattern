package basejava.concurrency;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/23 15:20
 */
public class BasicThread implements Runnable{
    @Override
    public void run() {

    }

    static class Guard {
        private ThreadLocal<Integer> local=new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 1;
            }
        };
        private int ACCESS_ALLOWED = 1;
        public boolean giveAccess() {
            local.remove();
//            local.set();
            return 42 == ACCESS_ALLOWED;
        }

        public ThreadLocal<Integer> getLocal() {
            return local;
        }
    }

    static class Job implements Runnable{
        private static AtomicInteger count=new AtomicInteger(0);
        private final int id=count.addAndGet(1);
        private Guard guard;

        public Job(Guard guard) {
            this.guard = guard;
        }

        @Override
        public void run() {
            for(int i=0;i<10;i++){
                ThreadLocal<Integer> local=guard.getLocal();
                System.out.println("id="+id+" before local="+local.get());
                local.set(local.get()+1);
                System.out.println("id="+id+" after local="+local.get());
            }

        }
    }


    public static void main(String[] args) throws NoSuchFieldException,SecurityException,IllegalAccessException{

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);


        Guard guard = new Guard();
        System.out.println(guard.giveAccess());;   // false, no access




        // bypass
        Field field = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        field.setAccessible(true);

        unsafe.putInt(guard, unsafe.objectFieldOffset(field), 42); // memory corruption
        field.set(guard,1);
        System.out.println(guard.giveAccess()); // true, access granted

        for(int i=0;i<4;i++){
            Executors.newCachedThreadPool().execute(
                    new Job(guard)
            );
        }

    }
}
