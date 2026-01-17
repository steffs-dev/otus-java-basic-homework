package ru.otus.java.basic.homeworx.homework_lesson_10.UserApp;

/**
 * @apiNote Класс отвечает за запуск программы
 */
public class Application {
    public static void main(String[] args) {
        UserHandler userHandler = new UserHandler();
        User[] arrayOfUsers = new User[10];
        userHandler.fillArrayWithUsers(arrayOfUsers);
        userHandler.printUsersOverCertainAge(arrayOfUsers, 40);
    }
}
