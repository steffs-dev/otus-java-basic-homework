package ru.otus.java.basic.homeworx.homework_lesson_21;

import java.sql.SQLOutput;
import java.time.Duration;
import java.time.Instant;

public class MultithreadApp {

    public static void timeCount() {
        Instant start = Instant.now();

        fillArray();

        Instant end = Instant.now();
        System.out.println("Время заполнения с 1 потоком: " +
                Duration.between(start, end).toMillis() + " мс");
    }

    private static void fillArray() {
        double[] array = new double[100_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }

    public static void main(String[] args) {
        timeCount();
    }
}
