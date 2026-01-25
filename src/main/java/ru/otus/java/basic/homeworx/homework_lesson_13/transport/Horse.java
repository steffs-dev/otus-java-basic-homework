package ru.otus.java.basic.homeworx.homework_lesson_13.transport;

import ru.otus.java.basic.homeworx.homework_lesson_13.Terrain;

/**
 * Класс лошадь, расширяющий абстрактный класс Transport
 */
public class Horse extends Transport {

    public Horse() {
        type = "Лошадь";
        resource = 300;
    }

    /**
     * @param terrain - заданная местность
     * @return true - если может передвигаться по заданной местности, false - если нет
     * @apiNote лошадь не может ехать по болоту
     */
    @Override
    public boolean isAbleToPassTerrain(Terrain terrain) {
        if (terrain.equals(Terrain.SWAMP)) {
            System.out.println(type + " не может ехать по этой местности");
            return false;
        }
        return true;
    }
}
