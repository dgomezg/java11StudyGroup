package dev.dgomezg.playground;

public class DaemonThreads {

    public static volatile boolean running = true;

    public static void main(String[] args) {
        System.out.println("Starting....");


        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.printf("Starting %s :", Thread.currentThread().getName());

                while (running) {
                    System.out.print(".");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted, we should exit.");
                    }
                }

            }
        }, "Counter");

        t.setDaemon(false);
        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false;
        System.out.println("Main thread finished");
    }
}
