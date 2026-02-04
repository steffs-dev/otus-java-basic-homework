package ru.otus.java.basic.homeworx.homework_lesson_17.solution_with_set;

import java.util.Objects;

/**
 * Класс контакт для хранения данных о контакте в телефонной книге
 * number - номер телефона. Тип данных номера - String, чтобы в дальнейшем иметь возможность изменения формата
 * номера за счет добавления "+", "-"
 * surname - фамилия
 * name - имя
 * patronymic - отчество
 *
 * @apiNote все параметры инициализируются при создании объекта класса
 */
public class Contact {
    private final String number;
    private final String surname;
    private final String name;
    private final String patronymic;


    public Contact(String number, String surname, String name, String patronymic) {
        this.number = number;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    /**
     * геттеры
     *
     * @return значения соответствующих полей класса
     */
    public String getNumber() {
        return number;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Соединяет в одну строку фамилия, имя и отчество (при наличии)
     *
     * @return
     */
    public String getFullName() {
        if (patronymic.isBlank()) {
            return surname + " " + name;
        }
        return surname + " " + name + " " + patronymic;
    }

    @Override
    public String toString() {
        String shortInfo = "* Номер: " + number +
                ", Фамилия: " + surname +
                ", имя: " + name;
        if (patronymic.isBlank()) {
            return shortInfo;
        }

        return shortInfo + ", отчество: " + patronymic;
    }
}
