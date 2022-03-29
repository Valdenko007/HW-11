package com.goit.task1old;

public class SessionTimeThread  extends  Thread{
    private final SessionTime count = new SessionTime();
    private int countSec = count.getCounter();
    private static final Thread thread = Thread.currentThread();

    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                sleep(1000);
                countSec++;
                count.setCounter(countSec);
                if (countSec % 5 != 0) {
                    messageAboutSecond();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void messageAboutSecond(){
        System.out.printf("Прошло %s сек...%n", countSec);

    }
}
