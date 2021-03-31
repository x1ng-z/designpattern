package basejava;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/26 13:54
 */
public class EnumT {

    public static ThreadLocal<Integer> count=new ThreadLocal<>();
    public   ThreadLocal<Status> status=new ThreadLocal<Status>(){
        @Override
        protected Status initialValue() {
            return Status.FAILED;
        }
    };
    enum Status{
        SUCCESS,
        FAILED;
        private int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread()+"EnumT{" +
                "status=" + status.get().getI() +
                '}';
    }


    public static void main(String[] args) {

         Map<Integer, String> modulepool = new ConcurrentHashMap<>();
        modulepool.put(1,"test");
        modulepool.put(2,"aa");
        Iterator iterator = modulepool.values().iterator();
        while (iterator.hasNext()) {
            String pySession = (String) iterator.next();
            if (pySession.equals("test")) {
                iterator.remove();
            }
        }

        System.out.println(modulepool.toString());








        System.out.println(System.currentTimeMillis());
        System.out.println(1616836529353L);



        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                EnumT enumT=new EnumT();
                enumT.count.set(new Integer(1));

                long runtime=System.currentTimeMillis();
                while (System.currentTimeMillis()-runtime<1000*10){
                    System.out.println("1:  "+  enumT.count.get());
                    try {
                        Thread.sleep(1000*1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                EnumT enumT2=new EnumT();
                enumT2.count.set(new Integer(2));

                long runtime=System.currentTimeMillis();
                while (System.currentTimeMillis()-runtime<1000*10){
                    System.out.println("2:  "+enumT2.count.get());
                    try {
                        Thread.sleep(1000*1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }


}
