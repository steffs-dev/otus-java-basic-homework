package ru.otus.java.basic.homeworx.homework_lesson_19.nio;

/**
 * Класс для запуска приложения
 */
public class FilesApp {

    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.workWithFiles();
    }
}
