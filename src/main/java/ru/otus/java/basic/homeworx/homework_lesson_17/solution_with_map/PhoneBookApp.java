package ru.otus.java.basic.homeworx.homework_lesson_17.solution_with_map;

/**
 * Класс для запуска приложения
 */
public class PhoneBookApp {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("79111111111", "Ivanov Ivan Ivanovich");
        phoneBook.add("89222222222", " Petrov Petr Petrovich");
        phoneBook.add("79333333333", "Olegov  Oleg Olegovich");
        System.out.println("-----------------------");
        phoneBook.add("69112345678", "Sergeev Sergei Sergeevich");
        phoneBook.add("791123456782", "Ivanov Ivan Ivanovich");
        phoneBook.add("79555555555", " ");
        phoneBook.add("79555555555", null);
        phoneBook.add("89222222222", " Fedorov Fedor Fedorovich");

        System.out.println("-----------------------");
        phoneBook.add("79666666666", "Olegov Oleg Olegovich");
        phoneBook.add("79777777777", "Olegov Fedor Fedorovich");

        phoneBook.printRecordsMap();

        phoneBook.find(" Ivanov");
        phoneBook.find("Olegov ");
        phoneBook.find("Ivanova");
        phoneBook.find(" ");
        System.out.println("-----------------------");
        phoneBook.containsPhoneNumber("79777777777");
        phoneBook.containsPhoneNumber("79777777778");
        phoneBook.containsPhoneNumber("7977777777");


    }

}
