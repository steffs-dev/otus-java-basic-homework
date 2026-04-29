package ru.otus.java.basic.homeworx.homework_lesson_32.Processors;

import ru.otus.java.basic.homeworx.homework_lesson_32.HttpServer;
import ru.otus.java.basic.homeworx.homework_lesson_32.RequestParser;

import java.io.PrintWriter;

/**
 * Обработчик для корневого маршрута "GET /".
 * Возвращает простую HTML-страницу с приветствием.
 */
public class GreetingProcessor implements Processor {

    @Override
    public void handle(RequestParser requestParser, PrintWriter writer, HttpServer httpServer) {
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body><h1>Hi there!!!</h1></body></html>";
        writer.println(response);
    }
}
