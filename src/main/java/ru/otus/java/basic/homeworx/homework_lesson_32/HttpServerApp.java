package ru.otus.java.basic.homeworx.homework_lesson_32;

/**
 * Точка входа в приложение – создает и запускает HTTP-сервер.
 */
public class HttpServerApp {
    public static void main(String[] args) {
        new HttpServer().start();
    }
}
