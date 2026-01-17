package ru.otus.java.basic.homeworx.homework_lesson_10.UserApp;

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
}


