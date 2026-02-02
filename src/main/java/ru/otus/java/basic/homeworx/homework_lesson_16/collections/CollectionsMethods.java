package ru.otus.java.basic.homeworx.homework_lesson_16.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс с методами для работы с коллекцией List
 */
public class CollectionsMethods {

    /**
     * Метод для заполнения ArrayList значениями между заданными значениями min и max
     *
     * @param min - минимальное значение для заполнения ArrayList
     * @param max - максимальное значение для заполнения ArrayList (включительно)
     * @return заполненный ArrayList
     * @throws IllegalArgumentException - если min > max
     */
    public ArrayList<Integer> fillArrayList(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Недопустимое значение: min должен " +
                    "быть меньше или равен max");
        }
        // через цикл
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;

        // через стрим
//        return IntStream.rangeClosed(min, max).boxed()
//                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Метода для подсчета суммы элементов списка, значение которых выше порога (threshold)
     *
     * @param list - список Integer
     * @return - целочисленное значение суммы элементов, значение которых выше
     * порога (threshold), если таких элементов нет или список пустой, возвращается 0
     * @throws IllegalArgumentException - если список null или пустой
     */
    public int sumListElements(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Список должен не быть null или пустым");
        }

        // через цикл
        int threshold = 5;
        int sum = 0;
        for (Integer i : list) {
            if (i > threshold) sum += i;
        }
        return sum;

        // через стрим
//        return list.stream()
//                .filter(i -> i > 5)
//                .reduce(0, Integer::sum);
    }

    /**
     * Метод для замены значений списка на заданное число
     *
     * @param value - целочисленное значение, на которое заменяются все элементы списка
     * @param list  - список, элементы которого заменяются на заданное значение
     * @throws IllegalArgumentException - если список null или пустой
     */
    public void replaceListElements(int value, List<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Список должен не быть null или пустым");
        }
        list.replaceAll(i -> value);

    }

    /**
     * Метод для увеличения элементов списка на заданное значение
     *
     * @param value - целочисленное значение, на которое увеличиваются все элементы списка
     * @param list  - список, элементы которого увеличиваются на заданное значение
     * @throws IllegalArgumentException - если список null или пустой
     */
    public void increaseListElements(int value, List<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Список должен не быть null или пустым");
        }
        list.replaceAll(i -> i + value);
    }

    /**
     * Метод для вывода элементов списка в консоль
     *
     * @param list - список, элементы которого выводятся в консоль
     * @throws IllegalArgumentException - если список null или пустой
     * @apiNote в качестве дженерика указан wildcard
     */
    public void printListElements(List<?> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список должен не быть null");
        }
        list.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
