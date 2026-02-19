package ru.otus.java.basic.homeworx.homework_lesson_13.transport;

import ru.otus.java.basic.homeworx.homework_lesson_13.Terrain;

/**
 * Класс машина, расширяющий абстрактный класс Transport
 */
public class Car extends Transport {

    public Car() {
        type = "Машина";
        resource = 700;
    }

    /**
     * @param terrain - заданная местность
     * @return true - если может передвигаться по заданной местности, false - если нет
     * @apiNote машина может ехать только по равнине
     */
    @Override
    public boolean isAbleToPassTerrain(Terrain terrain) {
        if (!terrain.equals(Terrain.PLAIN)) {
            System.out.println(type + " не может ехать по этой местности");
            return false;
        }
        return true;
    }
}
