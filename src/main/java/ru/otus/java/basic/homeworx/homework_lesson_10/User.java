package ru.otus.java.basic.homeworx.homework_lesson_10;

import java.time.Year;
import java.util.Random;

/**
 * @apiNote Класс Пользователя
 * firstname - имя пользователя;
 * lastname - фамилия пользователя;
 * patronymic - отчество пользователя;
 * yearOfBirth - год рождения пользователя;
 * email - эл.почта пользователя
 */
public class User {
    private String firstname;
    private String lastname;
    private String patronymic;
    private int yearOfBirth;
    private String email;

    public User(String firstname, String lastname, String patronymic, int yearOfBirth, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Метод выводит в консоль информацию о пользователе в формате:
     * ФИО: фамилия имя отчество
     * Год рождения: год рождения
     * e-mail: email
     */
    public void printUserInformation() {
        String output = String.format("""
                        ФИО: %s %s %s
                        Год рождения: %d
                        email: %s
                        """,
                lastname, firstname, patronymic,
                yearOfBirth,
                email);
        System.out.println(output);
    }

    /**
     * Метод заполняет полученный массив объектами класса User.
     * Данные берутся поочередно из массивов firstnames, lastnames, patronymics, emails.
     * Год рождения заполняется псевдослучайным значением, полученным с помощью класса Random с
     * заданными минимальным и максимальным значениями.
     *
     * @param arrayOfUsers - пустой одномерный массив объектов типа User
     * @return полученный массив, заполненный экземплярами класса User
     */
    public static User[] fillArrayWithUsers(User[] arrayOfUsers) {
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
    public static void printUsersOverCertainAge(User[] arrayOfUsers, int age) {
        int currentYear = Year.now().getValue();
        for (int i = 0; i < arrayOfUsers.length; i++) {
            if ((currentYear - arrayOfUsers[i].getYearOfBirth()) > age) {
                arrayOfUsers[i].printUserInformation();
            }
        }
    }

    public static void main(String[] args) {
        User[] arrayOfUsers = new User[10];
        fillArrayWithUsers(arrayOfUsers);
        printUsersOverCertainAge(arrayOfUsers, 40);
    }
}


