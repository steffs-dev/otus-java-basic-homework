package ru.otus.java.basic.homeworx.homework_lesson_13;

/**
 * Перечисление вариантов местности
 */
public enum Terrain {
    DENSE_FOREST("Густой лес"),
    PLAIN("Равнина"),
    SWAMP("Болото");

    /**
     * name - значение для вывода в консоль
     */
    private final String name;

    Terrain(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

