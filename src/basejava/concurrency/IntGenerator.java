package basejava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/24 13:23
 */
class EvenChecker implements Runnable {
    private IntGenerator generator;

    public EvenChecker(IntGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        while (!generator.isIscancel()) {
            int pendingcheck = generator.next();
            if (pendingcheck % 2 != 0) {
                System.out.println("get a no even number!!");
                generator.cancel();
            }
        }
    }
}

abstract public class IntGenerator {
    protected volatile int value;
    private boolean iscancel = false;

    public boolean isIscancel() {
        return iscancel;
    }

    public void cancel() {
        this.iscancel = true;
    }

    abstract int next();


    public static void test(IntGenerator gp,int count){
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<count;i++){
            exec.execute(new EvenChecker(gp));
        }
        exec.shutdown();;
    }
    public static void main(String[] args) {
        test(new EvenIntGeneratorBySynchrin(),10);
    }
}

class EvenIntGeneratorBySynchrin extends IntGenerator {
    @Override
    synchronized int next() {
        value++;
        value++;
        return value;
    }
}


class EvenIntGeneratorByLock extends IntGenerator {
    private Lock lock=new ReentrantLock();
    @Override
     int next() {
//        sun.misc.Unsafe UNSAFE = sun.misc.Unsafe.getUnsafe();
//        UNSAFE.park(true, deadline);
//        UNSAFE.unpark();
        lock.lock();
        try {
            value++;
            value++;
            return value;
        }finally {
            lock .unlock();
        }



    }
}