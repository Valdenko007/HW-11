package com.goit.task1old;

public class SessionTimePrinter extends Thread {
    private static final Thread thread = Thread.currentThread();
    private final SessionTime count = new SessionTime();

    @Override
    public void run() {
        while (!thread.isInterrupted()) {
            int countSec = count.getCounter();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (countSec % 5 == 0) {
                message();
            }
        }
    }

    private void message(){
        System.out.println("Прошло 5 сек...");
    }
}
