package ru.otus.java.basic.homeworx.homework_lesson_12;

/**
 * Класс для задания начальных значений и запуска приложения
 */
public class FoodApp {
    public static void main(String[] args) {
        Plate plate = new Plate(5, 10);
        Cat[] cats = {
                new Cat("Barsik", 6),
                new Cat("Pushok", 5),
                new Cat("Murzik", 4),
                new Cat("Vasya", 5),
                new Cat("Rizhik", 0),

        };

        for (Cat cat : cats) {
            cat.eat(plate);
            cat.info();
        }
        plate.info();
    }
}
