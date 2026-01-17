package ru.otus.java.basic.homeworx.homework_lesson_10.BoxApp;

/**
 * @apiNote Класс отвечает за запуск программы
 */
public class Application {
    public static void main(String[] args) {
        BoxHandler boxHandler = new BoxHandler();
        boxHandler.start();
    }
}
