package com.goit.Task1New;

public class SecondRun {
    private static final Object second = new Object();

    public static void main(String[] args)  {

        int timer = 0;

        Thread fiveSecondsTimer = new Thread(() -> {
            while (true) {
                synchronized (second) {
                    try {
                        second.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Прошло 5 сек...");
            }
        });

        fiveSecondsTimer.start();

        while (true) {
            if (timer % 5 != 0) {
                System.out.println(timer);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            timer++;
            if (timer % 5 == 0) {
                synchronized (second) {
                    second.notify();
                }
            }
        }
    }

}
