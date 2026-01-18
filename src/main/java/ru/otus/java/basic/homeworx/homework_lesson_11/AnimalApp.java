package ru.otus.java.basic.homeworx.homework_lesson_11;

/**
 * Класс для запуска приложения
 */
public class AnimalApp {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", 5, 50);
        cat.printActionInfo(cat.run(45));
        cat.info();
        System.out.println("---------------------------------");

        Dog dog = new Dog("Druzhok", 10, 5, 100);
        dog.printActionInfo(dog.run(100));
        dog.info();
        System.out.println("---------------------------------");

        Dog dog1 = new Dog("Mukhtar", 15, 7, 150);
        dog1.printActionInfo(dog1.swim(40));
        dog1.info();
        System.out.println("---------------------------------");

        Horse horse = new Horse("Iskra", 20, 10, 200);
        horse.printActionInfo(horse.run(150));
        horse.info();
        System.out.println("---------------------------------");

        Horse horse1 = new Horse("Veter", 25, 15, 250);
        horse1.printActionInfo(horse1.swim(200));
        horse1.info();
        System.out.println("---------------------------------");
    }
}
