package ru.otus.java.basic.homeworx.homework_lesson_32;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworx.homework_lesson_32.Entities.Item;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Основной класс HTTP-сервера.
 * Принимает входящие соединения, передает их в пул потоков,
 * обеспечивает graceful shutdown и предоставляет доступ к DBService.
 */
public class HttpServer {
    private final int PORT = 8081;
    private final ExecutorService executor;
    private final DBService dbService;

    private static final Logger log = LogManager.getLogger(HttpServer.class);

    public HttpServer() {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(),
                threadFactory());
        try {
            this.dbService = new DBService();
        } catch (SQLException e) {
            log.error("Ошибка при подключении к БД. {}", e.getMessage());
            throw new RuntimeException("Ошибка при подключении к БД. " + e);
        }
    }

    /**
     * Запускает сервер: слушает порт, принимает сокеты, отправляет задачи в
     * ExecutorService
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log.info("HttpServer started on port {}", PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                log.info("Accepted connection from {}", socket.getInetAddress().getHostName());
                RequestHandler requestHandler = new RequestHandler(socket, this);
                executor.submit(requestHandler);
            }

        } catch (IOException e) {
            log.error("Failed to start http server. {}", e.getMessage());
            throw new RuntimeException("Failed to start http server.", e);
        } finally {
            stop();
        }
    }

    /**
     * Возвращает список всех товаров (делегат DBService, чтобы прямой доступ к
     * DBService был только у сервера)
     */
    public List<Item> getItems() throws SQLException {
        return dbService.getAll();
    }

    /**
     * Сохраняет товар (делегат DBService, чтобы прямой доступ к DBService
     * был только у сервера)
     */
    public Item save(Item item) throws SQLException {
        return dbService.save(item);
    }

    /**
     * Фабрика потоков с заданием имени и обработчиком непойманных исключений.
     */
    private ThreadFactory threadFactory() {
        return r -> {
            Thread t = new Thread(r);
            t.setName("HttpServer-Thread-" + t.threadId());
            t.setUncaughtExceptionHandler((thread, e) -> {
                log.error("Uncaught exception in HttpServer-Thread-{}, {}",
                        thread.threadId(), e);
            });
            return t;
        };
    }

    /**
     * Закрывает соединение с БД и останавливает пул потоков.
     */
    private void stop() {
        try {
            dbService.closeConnection();
            shutdown();
        } catch (SQLException e) {
            log.error("Failed to close connection. {}", e.getMessage());
        }
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
                log.info("{} заданий было прервано в try", tasksInTry.size());
            }
        } catch (InterruptedException e) {
            List<Runnable> tasksInCatch = executor.shutdownNow();
            log.info("{} заданий было прервано в catch", tasksInCatch.size());
            Thread.currentThread().interrupt();
        }
    }
}
