package ru.otus.java.basic.homeworx.homework_lesson_32.Processors;

import ru.otus.java.basic.homeworx.homework_lesson_32.HttpServer;
import ru.otus.java.basic.homeworx.homework_lesson_32.RequestParser;

import java.io.PrintWriter;

/**
 * Интерфейс обработчика HTTP-запроса (аналог сервлета).
 * Каждая конкретная реализация отвечает за обработку определенного маршрута.
 */
public interface Processor {

    /**
     * Обрабатывает входящий запрос и формирует HTTP-ответ.
     *
     * @param requestParser объект с распарсенными данными запроса
     * @param writer        PrintWriter для отправки ответа клиенту
     * @param httpServer    ссылка на сервер (для доступа к БД)
     */
    void handle(RequestParser requestParser, PrintWriter writer, HttpServer httpServer);
}
