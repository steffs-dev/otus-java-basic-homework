package ru.otus.java.basic.homeworx.homework_lesson_16.employee;

/**
 * Класс работник
 * name - имя работника
 * age - возраст работника
 *
 * @apiNote отсутствует проверка на нал и нелогичные значения возраста
 */
public class Employee {
    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
