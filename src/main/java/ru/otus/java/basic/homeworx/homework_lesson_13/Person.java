package ru.otus.java.basic.homeworx.homework_lesson_13;

import ru.otus.java.basic.homeworx.homework_lesson_13.transport.Bike;
import ru.otus.java.basic.homeworx.homework_lesson_13.transport.Transport;

/**
 * Класс человек
 * name - имя человека
 * endurance - выносливость человека
 * travel - экземпляр класса путешествия, содержащий информацию о местности и дистанции, которые необходимо преодолеть
 * currentTransport - транспорт, имеющийся в распоряжении человека (может отсутствовать)
 */
public class Person {
    private final String name;
    private int endurance;
    private final Travel travel;
    private Transport currentTransport;

    /**
     * Конструктор, в котором не задается транспорт - случай, когда транспорт отсутствует
     *
     * @param name   - имя человека
     * @param travel - экземпляр класса путешествия
     *               выносливость устанавливается при создании экземпляра класса человек.
     */
    public Person(String name, Travel travel) {
        this.name = name;
        this.travel = travel;
        endurance = 100;
    }

    /**
     * Конструктор, в котором задается транспорт - случай, когда транспорт имеется
     *
     * @param name             - имя человека
     * @param travel           - экземпляр класса путешествия
     * @param currentTransport - экземпляр класса транспорт, если это велосипед, то ресурс велосипеда=выносливости
     *                         человека, выносливость человека обнуляется (передается вся выносливость)
     * @apiNote если транспорт - это велосипед, в качестве его ресурса передается выносливость человека
     */
    public Person(String name, Travel travel, Transport currentTransport) {
        this(name, travel);
        this.currentTransport = currentTransport;
        if (this.currentTransport instanceof Bike) {
            ((Bike) this.currentTransport).setResource(endurance);
            endurance = 0;
        }
    }

    @Override
    public String toString() {
        return "------------------------\n" +
                "Участник:\n" +
                "* имя = " + name + "\n" +
                "* выносливость = " + endurance + "\n" +
                "* дистанция = " + travel.getDistance() + "\n" +
                "* местность = " + travel.getTerrain().getName() + "\n" +
                "* транспорт = " + ((currentTransport == null) ? "нет" : currentTransport.getType());
    }

    /**
     * Метод для вывода в консоль информации о человеке
     */
    public void info() {
        System.out.println(this);
    }

    /**
     * Метод, имитирующий преодоление транспортом заданной дистанции.
     *
     * @param distance - дистанция, которую необходимо пройти
     * @return - пройденное расстояние
     * @apiNote если транспорт - велосипед, человека возвращается часть не использованной при езде на велосипеде
     * выносливости. Метод используется, если транспорт отсутствует и человек идет пешком
     * или ресурс транспорта иссяк и после этого человек продолжает преодоление дистанции пешком.
     * При преодолении дистанции тратятся ресурсы
     */
    public int traveledDistance(int distance) {
        int traveledDistance = 0;
        if (currentTransport instanceof Bike) {
            endurance = ((Bike) currentTransport).getResource();
        }
        if (distance - endurance < 0) {
            endurance -= distance;
            traveledDistance = distance;
            System.out.println("Человек прошел пешком: " + traveledDistance + " км.");
            return traveledDistance;
        }
        traveledDistance = endurance;
        endurance = 0;
        System.out.println("Человек прошел пешком: " + traveledDistance + " км.");
        return traveledDistance;
    }

    /**
     * Метод для определения пройдена или нет дистанция (транспорт + человек). Если дистанция не пройдена полностью, то выводит
     * в консоль информацию об оставшемся пути
     *
     * @param terrain  - заданная местность
     * @param distance - дистанция, которую необходимо пройти
     * @return - true, если дистанция пройдена; false - если не пройдена
     */
    public boolean isDistanceCovered(Terrain terrain, int distance) {
        if (currentTransport != null) {
            distance -= currentTransport.travel(terrain, distance);
            if (distance <= 0) {
                return true;
            }
        }
        int traveledDistance = traveledDistance(distance);
        distance -= traveledDistance;
        if (distance > 0) {
            System.out.println("До конца дистанции осталось: " + distance);
            return false;
        }
        return true;
    }

    /**
     * Метода для вывода в консоль информации о том пройдена дистанция или нет
     */
    public void travelResultInfo() {
        if (isDistanceCovered(travel.getTerrain(), travel.getDistance())) {
            System.out.println("Ура! " + name + " преодолел дистанцию");
        } else {
            System.out.println("Нужно идти на поиски " + name);
        }
    }
}
