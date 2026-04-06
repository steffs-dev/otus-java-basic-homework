package ru.otus.java.basic.homeworx.homework_lesson_32;

import ru.otus.java.basic.homeworx.homework_lesson_32.Processors.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Диспетчер маршрутов.
 * Сопоставляет строку "routingKey" из RequestParser с соответствующим Processor.
 * Если обработчик не найден, вызывает DefaultProcessor.
 */
public class Dispatcher {
    private final Map<String, Processor> handlers;
    private final DefaultProcessor defaultProcessor;

    public Dispatcher() {
        this.handlers = new HashMap<>();
        this.defaultProcessor = new DefaultProcessor();
        handlers.put("GET /", new GreetingProcessor());
        handlers.put("GET /items", new GetItemsProcessor());
        handlers.put("POST /items", new CreateItemsProcessor());
    }

    /**
     * Находит обработчик по маршруту (routingKey) и делегирует ему обработку запроса.
     */
    public void handle(RequestParser requestParser, PrintWriter writer,
                       HttpServer httpServer) throws IOException {
        if (!handlers.containsKey(requestParser.getRoutingKey())) {
            defaultProcessor.handle(requestParser, writer, httpServer);
            return;
        }
        handlers.get(requestParser.getRoutingKey()).handle(requestParser, writer, httpServer);
    }
}
