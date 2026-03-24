package ru.otus.java.basic.homeworx.homework_lesson_27;

/**
 * Абстрактный класс, представляющий фрукт.
 * Содержит общие свойства: вес и название.
 * weight - вес фрукта в условных единицах.
 * name - название фрукта (например, "Apple", "Orange").
 */
abstract public class Fruit {
    private int weight;
    private String name;

    /**
     * Конструктор фрукта.
     * @param weight вес фрукта
     * @param name   название фрукта
     */
    public Fruit(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    /** Возвращает вес фрукта. */
    public int getWeight() {
        return weight;
    }

    /** Возвращает название фрукта. */
    public String getName() {
        return name;
    }
}
