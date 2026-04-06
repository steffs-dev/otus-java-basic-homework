package ru.otus.java.basic.homeworx.homework_lesson_32.Processors;

import ru.otus.java.basic.homeworx.homework_lesson_32.HttpServer;
import ru.otus.java.basic.homeworx.homework_lesson_32.RequestParser;

import java.io.PrintWriter;

/**
 * Обработчик по умолчанию (404 Not Found).
 * Используется, когда для запрошенного маршрута не зарегистрирован ни один
 * Processor.
 */
public class DefaultProcessor implements Processor {

    @Override
    public void handle(RequestParser requestParser, PrintWriter writer, HttpServer httpServer) {
        String response = "" +
                "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body><h1>404.. Page Not Found</h1></body></html>";
        writer.println(response);

    }
}
