package ru.otus.java.basic.homeworx.homework_lesson_16.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска приложения
 */
public class CollectionsApp {

    public static void main(String[] args) {
        CollectionsMethods collectionsMethods = new CollectionsMethods();
        List<Integer> emptyList = new ArrayList<>();
        List<Integer> nullList = null;
        List<Integer> integerList = collectionsMethods.fillArrayList(3, 7);
//        List<Integer> integerList = collectionsMethods.fillArrayList(1, 1);
//        List<Integer> integerList = collectionsMethods.fillArrayList(1, 0);

        collectionsMethods.printListElements(integerList);

        System.out.println(collectionsMethods.sumListElements(integerList));

        collectionsMethods.replaceListElements(1, integerList);
        collectionsMethods.printListElements(integerList);

        collectionsMethods.increaseListElements(4, integerList);
        collectionsMethods.printListElements(integerList);
    }


}
