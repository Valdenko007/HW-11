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

import java.util.StringJoiner;

public class FizzBuzz {
    static int n = 15;
    static int[] array;
    static String[] result;
    static StringJoiner joiner = new StringJoiner(", ");

    public static void main(String[] args) {
        generateArrOfNum(n);

        Thread A =  new Thread(() -> fizz(array, result));
        Thread B = new Thread(() -> buzz(array, result));
        Thread C = new Thread(() -> fizzbuzz(array, result));
        Thread D = new Thread(() -> number(array, result));

        A.start();
        B.start();
        C.start();
        D.start();

        try {
            A.join();
            B.join();
            C.join();
            D.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for (String s:result) {
            joiner.add(s);
        }

        System.out.println(joiner.toString());
    }

    public static void generateArrOfNum(int n){
        array = new int[n];
        result = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = i+1;
        }
    }

    public static void fizz(int[] array, String[] result){
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            if (temp%3==0 && temp%5!=0){
                result[i] = "fizz";
            }
        }
    }

    public static void buzz(int[] array, String[] result){
        for (int i = 0; i < array.length; i++){
            int temp = array[i];
            if(temp%3!=0 && temp%5==0){
                result[i] = "buzz";
            }
        }
    }

    public static void fizzbuzz(int[] array, String[] result){
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            if (temp%3==0 && temp%5==0){
                result[i] = "fizzbuzz";
            }
        }
    }

    public static void number(int[] array, String[] result){
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            if (temp%3!=0 && temp%5!=0){
                result[i] = String.valueOf(temp);

            }
        }
    }
}
