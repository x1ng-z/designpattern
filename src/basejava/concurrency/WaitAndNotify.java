package basejava.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zzx
 * @version 1.0
 * @date 2021/4/14 9:08
 */

class Car {
    private boolean waxon = false;

    public synchronized void waxed() {
        waxon = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxon = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() {
        try {
            while (!waxon) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void waitForBuffer() {
        try {
            while (waxon) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Waxon implements Runnable {
    private Car car;

    public Waxon(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("waxon");
                TimeUnit.MICROSECONDS.sleep(200);
                car.waxed();
                car.waitForBuffer();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end Wax on task");
    }
}

class Waxoff implements Runnable {
    private Car car;

    public Waxoff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                car.waitForWaxing();
                System.out.println("Wax off");
                TimeUnit.MICROSECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end Wax off task");
    }
}


public class WaitAndNotify {

    public static void main(String[] args) {
        Car car=new Car();
        ExecutorService service =Executors.newCachedThreadPool();

        service.execute(new Waxon(car));
        service.execute(new Waxoff(car));
    }
}
