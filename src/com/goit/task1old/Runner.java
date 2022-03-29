package com.goit.task1old;

/*

Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала сессии (запуска программы).
Другой ее поток выводит каждые 5 секунд сообщение  "Прошло 5 секунд".
Предусмотрите возможность ежесекундного оповещения потока, воспроизводящего сообщение, потоком, отсчитывающим время.

 */




public class Runner {
    private final int counter = 0;
    public static void main(String[] args) {

        Thread getSec = new SessionTimeThread();
        getSec.start();

        Thread getFive = new SessionTimePrinter();
        getFive.start();
    }
}
