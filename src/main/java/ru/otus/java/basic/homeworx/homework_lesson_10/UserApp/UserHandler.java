package ru.otus.java.basic.homeworx.homework_lesson_10.UserApp;

import java.time.Year;
import java.util.Random;

/**
 * @apiNote Класс содержит метод для наполнения массива User значениями и метод для фильтрации значений массива по возрасту
 */
public class UserHandler {
    /**
     * Метод заполняет полученный массив объектами класса User.
     * Данные берутся поочередно из массивов firstnames, lastnames, patronymics, emails.
     * Год рождения заполняется псевдослучайным значением, полученным с помощью класса Random с
     * заданными минимальным и максимальным значениями.
     *
     * @param arrayOfUsers - пустой одномерный массив объектов типа User
     * @return полученный массив, заполненный экземплярами класса User
     */
    public User[] fillArrayWithUsers(User[] arrayOfUsers) {
        String[] firstnames = {"Иван", "Петр", "Сергей"};
        String[] lastnames = {"Иванов", "Петров"};
        String[] patronymics = {"Иванович", "Петрович"};
        String[] emails = {"@gmail.com", "@mail.ru"};
        Random random = new Random();
        int min = 1950;
        int max = Year.now().getValue();
        int arrayCounter = 0;

        for (int i = 0; i < arrayOfUsers.length; i++) {
            for (int j = 0; j < lastnames.length; j++) {
                for (int k = 0; arrayCounter < arrayOfUsers.length && k < patronymics.length; k++) {
                    int randomNum = random.nextInt((max - min) + 1) + min;
                    String email = firstnames[i] + i + j + k + emails[k];
                    arrayOfUsers[arrayCounter] = new User(
                            firstnames[i],
                            lastnames[j],
                            patronymics[k],
                            randomNum,
                            email
                    );
                    arrayCounter++;
                }
            }
        }
        return arrayOfUsers;
    }

    /**
     * Метод выводит в консоль информацию о пользователях старше заданного возраста
     * Сравнение осуществляется с текущим годом
     *
     * @param arrayOfUsers массив, заполненный экземплярами класса User
     * @param age          возраст пользователей, подлежащих выводу в консоль
     */
    public void printUsersOverCertainAge(User[] arrayOfUsers, int age) {
        int currentYear = Year.now().getValue();
        for (int i = 0; i < arrayOfUsers.length; i++) {
            if ((currentYear - arrayOfUsers[i].getYearOfBirth()) > age) {
                arrayOfUsers[i].printUserInformation();
            }
        }
    }
}
