package ru.otus.java.basic.homeworx.homework_lesson_21;

import java.time.Duration;
import java.time.Instant;

public class MultithreadApp {
    static int arraySize = 100_000_000;
    static long durationOneThread;
    static long durationFourThreads;

    private static void fillArrayWithOneThread() {
        System.out.println("Однопоточное заполнение запущено, ожидаем выполнения...");
        Instant startTime = Instant.now();

        double[] array = new double[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }

        Instant endTime = Instant.now();
        durationOneThread = Duration.between(startTime, endTime).toMillis();

        System.out.printf("Элемент 1 = %f, элемент %d= %f, элемент %d= %f\n",
                array[1], arraySize / 2, array[arraySize / 2], arraySize, array[arraySize - 1]);
        System.out.println("Однопоточное заполнение завершено\n");
    }

    private static void fillArrayWithFourThreads() {
        System.out.println("Многопоточное заполнение запущено, ожидаем выполнения...");
        Instant startTime = Instant.now();

        double[] array = new double[arraySize];
        int threadsAmount = 4;
        int part = arraySize / threadsAmount;
        Thread[] threads = new Thread[threadsAmount];

        for (int i = 0; i < threadsAmount; i++) {
            int start = i * part;
            int end = (i == threadsAmount - 1) ? arraySize : start + part;

            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    array[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Instant endTime = Instant.now();
        durationFourThreads = Duration.between(startTime, endTime).toMillis();

        System.out.printf("Элемент 1 = %f, элемент %d= %f, элемент %d= %f\n",
                array[1], arraySize / 2, array[arraySize / 2], arraySize, array[arraySize - 1]);
        System.out.println("Многопоточное заполнение завершено\n");
    }

    static void durationDifference() {
        System.out.println("Время заполнения:\n" +
                "   1 потоком: " + durationOneThread + " мс\n" +
                "   4 потоками:" + durationFourThreads + " мс\n");
        System.out.printf("4 потока выполнили работу в %f раз быстрее, чем 1 поток\n",
                durationOneThread / (double) durationFourThreads);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread oneThread = new Thread(MultithreadApp::fillArrayWithOneThread);
        oneThread.start();
        oneThread.join();

        fillArrayWithFourThreads();

        durationDifference();
    }
}