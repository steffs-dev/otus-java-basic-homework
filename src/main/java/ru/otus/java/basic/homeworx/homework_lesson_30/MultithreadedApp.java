package ru.otus.java.basic.homeworx.homework_lesson_30;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Многопоточное приложение, которое выводит последовательность символов с использованием пула потоков
 * и синхронизацией.
 * executor - сервис для работы с пулом потоков
 * lock - общий монитор для синхронизации доступа к переменной symbol и wait/notify
 * COUNT - количество повторений последовательности ABC
 * symbol - текущий символ, который должен быть напечатан. Доступ к нему всегда происходит
 * внутри synchronized-блока, поэтому без volatile
 */
public class MultithreadedApp {
    private ExecutorService executor;
    private static final Object lock = new Object();
    private final int COUNT = 5;
    private char symbol = 'A';

    /**
     * Конструктор
     * Создается пул потоков фиксированного размера с тремя потоками и фабрикой потоков
     */
    public MultithreadedApp() {
        executor = Executors.newFixedThreadPool(3, getThreadFactory());
    }

    /**
     * Метод запускает три задачи, каждая отвечает за печать своего символа
     * После отправки задач инициирует завершение пула.
     * Каждая задача получает свой инициализирующий символ (букву, которую печатает)
     * и следующий символ, который должен быть установлен после печати
     */
    public void start() {
        executor.execute(() -> {
            printSymbol('A', 'B');
        });
        executor.execute(() -> {
            printSymbol('B', 'C');
        });
        executor.execute(() -> {
            printSymbol('C', 'A');
        });

        shutdown();
    }

    /**
     * Метод создает кастомные потоки с заданными именами и обработчиком неперехваченных исключений.
     *
     * @return фабрика потоков
     */
    private ThreadFactory getThreadFactory() {
        return new ThreadFactory() {
            private final AtomicInteger count = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("MultithreadedApp-Thread-" + count.getAndIncrement());
                t.setUncaughtExceptionHandler((tr, e) ->
                        System.out.printf("Uncaught exception in thread %s: %s",
                                tr.getName(), e.getMessage()));
                return t;
            }
        };
    }

    /**
     * Корректно завершает работу пула:
     * shutdown() запрещает прием новых задач, но дает время запущенным задачам завершиться.
     * awaitTermination() ожидает окончания всех задач или истечения таймаута.
     * Если таймаут истек, shutdownNow() пытается принудительно остановить оставшиеся задачи.
     * При прерывании текущего потока во время ожидания также вызывается shutdownNow()
     * и восстанавливается статус прерывания.
     */
    private void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                List<Runnable> tasksInTry = executor.shutdownNow();
                System.out.println(tasksInTry.size() + " заданий было прервано в try");
            }
        } catch (InterruptedException e) {
            List<Runnable> tasksInCatch = executor.shutdownNow();
            System.out.println(tasksInCatch.size() + " заданий было прервано в catch");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Метод печатает символ init COUNT раз, синхронизируясь с другими потоками.
     * Каждый поток ожидает своей очереди – пока текущий символ (symbol) не станет равен init.
     * Когда наступает его очередь, он печатает символ, переключает symbol на next и будит все
     * ожидающие потоки.
     * Процесс повторяется COUNT раз для каждого потока.
     *
     * @param init буква, которую данный поток печатает ('A', 'B' или 'C')
     * @param next следующая буква, которую должен напечатать следующий поток
     */
    private void printSymbol(char init, char next) {
        synchronized (lock) {
            for (int i = 0; i < COUNT; i++) {
                while (symbol != init) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.print(symbol);
                symbol = next;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        MultithreadedApp app = new MultithreadedApp();
        app.start();
    }
}
