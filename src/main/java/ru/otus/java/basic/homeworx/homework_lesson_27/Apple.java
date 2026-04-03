package ru.otus.java.basic.homeworx.homework_lesson_27;

/**
 * Класс яблока, наследник Fruit.
 * Все яблоки имеют название "Apple".
 */
public class Apple extends Fruit {

    /**
     * Конструктор яблока.
     * @param weight вес яблока
     */
    public Apple(int weight) {
    super(weight, "Apple");
    }
}
