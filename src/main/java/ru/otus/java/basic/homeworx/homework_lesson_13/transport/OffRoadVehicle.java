package ru.otus.java.basic.homeworx.homework_lesson_13.transport;

import ru.otus.java.basic.homeworx.homework_lesson_13.Terrain;

/**
 * Класс вездеход, расширяющий абстрактный класс Transport
 */
public class OffRoadVehicle extends Transport {

    public OffRoadVehicle() {
        type = "Вездеход";
        resource = 500;
    }

    /**
     * @param terrain - заданная местность
     * @return true
     * @apiNote вездеход может ехать по любой местности
     */
    @Override
    public boolean isAbleToPassTerrain(Terrain terrain) {
        return true;
    }
}
