package ru.otus.java.basic.homeworx.homework_lesson_13.transport;

import ru.otus.java.basic.homeworx.homework_lesson_13.Terrain;

/**
 * Класс велосипед, расширяющий абстрактный класс Transport
 */
public class Bike extends Transport {

    public Bike() {
        type = "Велосипед";
    }

    /**
     * Геттер для ресурса
     *
     * @return ресурс
     * @apiNote используется для получения остатка выносливости, чтобы присвоить его человеку, тк при езде
     * велосипеда тратятся ресурсы человека
     */
    public int getResource() {
        return resource;
    }

    /**
     * Сеттер для ресурса
     *
     * @param resource - выносливость человека
     * @apiNote для присвоения ресурсу велосипеда выносливости человека, тк при езде велосипеда тратятся ресурсы человека
     */
    public void setResource(int resource) {
        this.resource = resource;
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
