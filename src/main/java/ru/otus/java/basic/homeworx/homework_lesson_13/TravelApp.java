package ru.otus.java.basic.homeworx.homework_lesson_13;

import ru.otus.java.basic.homeworx.homework_lesson_13.transport.Bike;
import ru.otus.java.basic.homeworx.homework_lesson_13.transport.Car;
import ru.otus.java.basic.homeworx.homework_lesson_13.transport.Horse;
import ru.otus.java.basic.homeworx.homework_lesson_13.transport.OffRoadVehicle;

/**
 * Класс для запуска приложения
 */
public class TravelApp {
    public static void main(String[] args) {
        Travel travel1 = new Travel(Terrain.PLAIN, 1000);
        Travel travel2 = new Travel(Terrain.PLAIN, 800);
        Travel travel3 = new Travel(Terrain.PLAIN, 400);
        Travel travel4 = new Travel(Terrain.SWAMP, 600);
        Travel travel5 = new Travel(Terrain.DENSE_FOREST, 800);
        Travel travel6 = new Travel(Terrain.DENSE_FOREST, 50);
        Travel travel7 = new Travel(Terrain.SWAMP, 150);

        Person[] persons = {
                new Person("Boris", travel1, new Car()),
                new Person("Ivan", travel2, new Car()),
                new Person("Anton", travel4, new Car()),
                new Person("Petr", travel3, new Horse()),
                new Person("Sergey", travel5, new Horse()),
                new Person("Arthur", travel4, new OffRoadVehicle()),
                new Person("Max", travel5, new OffRoadVehicle()),
                new Person("Pavel", travel6),
                new Person("Fedor", travel7),
                new Person("Viktor", travel6, new Bike()),
                new Person("Oleg", travel7, new Bike()),
        };

        for (Person person : persons) {
            person.info();
            person.travelResultInfo();
        }

        }

    }

