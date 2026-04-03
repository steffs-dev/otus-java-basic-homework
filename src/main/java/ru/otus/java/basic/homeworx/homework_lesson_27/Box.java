package ru.otus.java.basic.homeworx.homework_lesson_27;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметризованный класс, представляющий коробку с фруктами
 * (можно класть только фрукты и его потомков)
 * storage - список для хранения фруктов
 * name - имя коробки (для идентификации в выводе)
 * type - класс типа T, используется для проверки совместимости при перемещении
 * @param <T> тип фруктов, хранящихся в коробке (должен быть наследником Fruit)
 */
public class Box<T extends Fruit> {
    private final List<T> storage;
    private final String name;
    private final Class<T> type;

    /**
     * Конструктор коробки.
     * @param name имя коробки
     * @param type объект Class, соответствующий типу T
     */
    public Box(String name, Class<T> type) {
        storage = new ArrayList<>();
        this.name = name;
        this.type = type;
    }

    /**
     * Добавляет фрукт в коробку.
     * @param item добавляемый фрукт
     */
    public void add(T item) {
        storage.add(item);
    }

    /**
     * Вычисляет суммарный вес всех фруктов в коробке.
     * @return суммарный вес
     */
    public int weight() {
        return storage.stream().mapToInt(T::getWeight).sum();
    }

    /** Возвращает список фруктов, хранящихся в коробке. */
    public List<T> getStorage() {
        return storage;
    }

    /** Возвращает имя коробки. */
    public String getName() {
        return name;
    }

    /** Возвращает класс типа T. */
    public Class<T> getType() {
        return type;
    }

    /**
     * Перемещает все фрукты, совместимые с коробкой назначения, из текущей коробки в указанную.
     * Совместимость определяется по типу: фрукт может быть перемещен, если его класс
     * соответствует типу коробки назначения (dest.getType().isInstance(item)).
     * В коробку Fruit можно переместить все фрукты, в коробку Orange/Apple только
     * соответственно, Orange/Apple.
     *
     * @param dest коробка, в которую перемещаются фрукты
     * @param <U>  тип фруктов в коробке назначения (должен быть наследником Fruit)
     */
    public <U extends Fruit> void moveFruits(Box<U> dest) {
        List<U> listToMove = new ArrayList<>();
        boolean flag = false;
        for (Fruit item : storage) {
            if (dest.getType().isInstance(item)) {
                listToMove.add((U) item);
                flag = true;
            }
        }
        dest.getStorage().addAll(listToMove);
        storage.removeAll(listToMove);
        System.out.printf("Фрукты типа %s %sперемещены из %s в %s\n",
                dest.getType().getSimpleName(),
                (flag) ? "" : "не ",
                name,
                dest.getName());
    }

    /**
     * Сравнивает вес текущей коробки с весом другой коробки.
     * @param box другая коробка (может содержать любой тип фруктов)
     * @return true, если веса равны
     */
    public boolean compare(Box<? extends Fruit> box) {
        return this.weight() == box.weight();
    }

    /**
     * Выводит в консоль результат сравнения весов двух коробок.
     * @param box другая коробка для сравнения
     */
    public void printCompare(Box<? extends Fruit> box) {
        System.out.printf("Коробка %s и коробка %s %sравны\n",
                getName(), box.getName(),
                compare(box) ? "" : "не");
    }

    /**
     * Выводит в консоль содержимое коробки: имя и вес каждого фрукта,
     * а затем разделительную линию.
     */
    public void printInfo() {
        System.out.println("Box " + name + ":");
        for (Fruit fruit : storage) {
            System.out.println(fruit.getName() + " вес: " + fruit.getWeight());
        }
        System.out.println("=".repeat(20));
    }
}
