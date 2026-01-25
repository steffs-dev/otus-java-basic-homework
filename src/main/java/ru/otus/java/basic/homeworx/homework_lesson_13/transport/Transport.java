package ru.otus.java.basic.homeworx.homework_lesson_13.transport;

import ru.otus.java.basic.homeworx.homework_lesson_13.Terrain;

/**
 * Родительский класс для типов транспорта
 * type - тип транспорта (инициализируется при создании соответствующего класса транспорта)
 * resource - ресурс, застрачиваемый при езде. Инициализируется при создании соответствующего класса транспорта,
 * кроме велосипеда, для которого устанавливается при создании класса Person (ресурс велосипеда равен ресурсу Person)
 */
public abstract class Transport {
    protected String type;
    protected int resource;

    /**
     * Геттер для типа транспорта
     *
     * @return - типа транспорта
     */
    public String getType() {
        return type;
    }

    /**
     * Метод для проверки возможности езды на заданной местности.
     *
     * @param terrain - заданная местность
     * @return true, если может ехать по terrain, false - если нет.
     * @apiNote поскольку возможность преодоления местности зависит от вида транспорта, реализация метода
     * осуществлена в классах-наследниках
     */
    public abstract boolean isAbleToPassTerrain(Terrain terrain);

    /**
     * Метод, имитирующий преодоление транспортом заданной дистанции
     *
     * @param terrain  - заданный вид местности
     * @param distance - заданная дистанция
     * @return - пройденное расстояние.
     * @apiNote Пройденное расстояние зависит от количества ресурсов, имеющихся у соответствующего вида
     * транспорта. При преодолении дистанции тратятся ресурсы. Если транспорт не может ехать по заданной местности,
     * пройденное расстояние = 0.
     * Выводит в консоль сообщения о пройденном расстоянии
     */
    public int travel(Terrain terrain, int distance) {
        int wayLength = 0;
        if (isAbleToPassTerrain(terrain)) {
            if ((distance - resource) <= 0) {
                resource -= distance;
                wayLength = distance;
                System.out.println("Человек проехал всю дистанцию " + wayLength + " км.");
                return wayLength;
            }
            wayLength = resource;
            resource = 0;
            System.out.println("Человек проехал: " + wayLength + " км.");
            return wayLength;
        }
        return wayLength;
    }
}
