package ru.otus.java.basic.homeworx.homework_lesson_13;

/**
 * Класс путешествие
 * terrain - заданная местность (задается при создании экземпляра класса)
 * distance - дистанция, которую необходимо пройти (задается при создании экземпляра класса)
 */
public class Travel {
    private final Terrain terrain;
    private final int distance;

    public Travel(Terrain terrain, int distance) {
        this.terrain = terrain;
        this.distance = distance;
    }

    /**
     * Геттер для приватного параметра terrain
     *
     * @return значение параметра terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Геттер для приватного параметра distance
     *
     * @return значение параметра distance
     */
    public int getDistance() {
        return distance;
    }
}
