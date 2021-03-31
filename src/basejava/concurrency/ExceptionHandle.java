package basejava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/3/24 12:40
 */
class MyUncatchtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getUncaughtExceptionHandler());
        System.out.println(t);
    }
}

class ExceptionThread implements Runnable {
    @Override
    public void run() {
        Thread thread=Thread.currentThread();
        System.out.println("run by "+thread);
        System.out.println("ExceptionThread en ="+thread.getUncaughtExceptionHandler());
        throw new RuntimeException("haha");
    }
}

class HandlerThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread thread=new Thread(r);
        thread.setUncaughtExceptionHandler(new MyUncatchtExceptionHandler());
        System.out.println("newThread en ="+thread.getUncaughtExceptionHandler());
        return thread;
    }
}
public class ExceptionHandle {

    public static void main(String[] args) {
        ExecutorService service =Executors.newCachedThreadPool(new HandlerThreadFactory());
        service.execute(new ExceptionThread());
    }
}
