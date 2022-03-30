package com.goit.task2;

/*

Напишите программу, которая выводит в консоль строку, состоящую из чисел от  1 до n, но с заменой некоторых значений:
если число делится на 3 - вывести "fizz"
если число делится на 5 - вывести "buzz"
если число делится на 3 и на 5 - вывести "fizzbuzz"
Например, для n = 15, ожидаемый результат: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
Программа должна быть многопоточной, работать с 4 потоками:
Поток A вызывает fizz() чтобы проверить делимость на 3 и вывести fizz.
Поток B вызывает buzz() чтобы проверить делимость на 5 и вывести buzz.
Поток C вызывает fizzbuzz() чтобы проверить делимость на 3 и 5 и вывести fizzbuzz.
Поток D вызывает number() чтобы вывести число.

*/


public class FizzBuzz {
    private static final Object M = new Object();
    private static int n = 1;


    public static void main(String[] args) {
        int number = 15;
        Thread A = new Thread(() -> fizz(number));
        Thread B = new Thread(() -> buzz(number));
        Thread C = new Thread(() -> fizzbuzz(number));
        Thread D = new Thread(() -> number(number));

        A.start();
        B.start();
        C.start();
        D.start();
    }

    private static void fizz(int number) {
        synchronized (M) {
            try {
                M.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        do {
            if (n % 3 == 0 & n % 5 != 0) {
                System.out.print("fizz ");
                synchronized (M) {
                    n++;
                    try {
                        M.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (M) {
                    M.notifyAll();
                }
            }
        } while (n <= number);
    }

    private static void buzz(int number) {
        synchronized (M) {
            try {
                M.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        do {
            if (n % 5 == 0 & n % 3 != 0) {
                System.out.print("buzz ");
                synchronized (M) {
                    n++;
                    try {
                        M.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (M) {
                    M.notifyAll();
                }
            }
        } while (n <= number);
    }

    private static void fizzbuzz(int number) {
        synchronized (M) {
            try {
                M.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(true) {
            if (n % 3 == 0 & n % 5 == 0) {
                System.out.print("fizzbuzz ");
                synchronized (M) {
                    n++;
                    try {
                        M.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (M) {
                    M.notifyAll();
                }
                if (n > number) {
                    break;
                }
            }
        }
    }

    private static void number(int number) {
        do {
            if (n % 3 != 0 & n % 5 != 0) {
                System.out.print(n + " ");
                synchronized (M) {
                    n++;
                }
            } else {
                synchronized (M) {
                    M.notifyAll();
                }
            }
        } while (n <= number);
    }
}