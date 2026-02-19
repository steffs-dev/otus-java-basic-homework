package ru.otus.java.basic.homeworx.homework_lesson_18;

import java.util.Collections;
import java.util.List;

/**
 * Параметризированный класс двоичного дерева
 * @param <T> тип данных, который должен имплементировать интерфейс Comparable.
 * root - корневой узел дерева
 * list - список объектов, из которых строится дерево (является параметром при создании дерева)
 * @apiNote дерево формируется при создании объекта из отсортированного списка
 */
public class BinarySearchTree<T extends Comparable<T>> implements SearchTree<T> {
    private Node<T> root;
    private List<T> list;

    /**
     * @throws IllegalArgumentException, если список null или пустой
     * @param list список объектов (может быть как отсортированным, так и нет)
     */
    public BinarySearchTree(List<T> list) {
        root = null;
        if(list == null || list.isEmpty()){
            throw new IllegalArgumentException("Список не должен быть null или пустым");
        }
        this.list = list;

        add(getSortedList());
    }

    /**
     * геттер для параметра list
     * @return - параметр объекта - list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Переопределенный метод имплементированного интерфейса. Предназначен для сортировки списка, полученного
     * в качестве параметра при создании объекта. Т.к. <T> имплементирует Comparable, имеется возможность
     * сортировки списка методом sort() в порядке, указанном при переопределении метода compareTo()
     * @return отсортированный список
     */
    @Override
    public List<T> getSortedList() {
        Collections.sort(list);
        return list;
    }

    /**
     * Метод для формирования дерева из сортированного списка. Вызывает метод build(), который рекурсивно вызывает сам себя
     * @param sortedList - сортированный список объектов
     *
     */
    public void add(List<T> sortedList) {
        root = build(sortedList, 0, sortedList.size() - 1);
    }

    /**
     * Метод для рекурсивного формирования ветвей дерева
     * @param sortedList - отсортированный список объектов
     * @param start - начальный индекс для итерации по списку
     * @param end - конечный индекс для итерации по списку
     * @return созданный узел дерева
     * @apiNote метод рекурсивно строит сначала левую ветку, затем правую. Базовый случай для прерывания метода - начальное значение больше конечного
     */
    private Node<T> build(List<T> sortedList, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        Node<T> node = new Node<>(sortedList.get(mid));

        node.setLeft(build(sortedList, start, mid - 1));
        node.setRight(build(sortedList, mid + 1, end));

        return node;
    }

    public void printSortedList(List<T> sortedList){
        System.out.println("Сформированный сортированный список: " + getList());
    }

    /**
     * Метод для графического отображения построенного дерева
     */
    public void printTree() {
        System.out.println("Сформированное дерево: ");
        drawTree(root, 0, "R: ");
    }

    /**
     * Метод для рекурсивной отрисовки дерева
     * @param node - узел
     * @param depth - глубина нахождения узла относительно корня (0)
     * @param prefix - указанием на левую(L:) или правую (R:) ветку
     */
    private void drawTree(Node<T> node, int depth, String prefix) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < depth; i++) {
            System.out.print("│   ");
        }

        String connector = depth == 0 ? "" : "└── ";
        System.out.println(connector + prefix + node.getData());

        drawTree(node.getLeft(), depth + 1, "L: ");
        drawTree(node.getRight(), depth + 1, "R: ");
    }

    /**
     * Метод для нахождения значения элемента в дереве. Вызывает метод recursiveSearch(), который рекурсивно вызывает сам себя
     * @param element to find
     * @return найденный элемент или null, если элемент не найден
     */
    @Override
    public T find(T element) {
        System.out.println("Результат поиска по дереву: ");
        return recursiveSearch(root, element);
    }

    /**
     * Метод для рекурсивного поиска элемента по дереву
     * @apiNote поиск осуществляется в соответствии с порядком, указанным при переопределении метода compareTo()
     * @param node - узел, в котором ищется заданное значение
     * @param element - заданное значение, которое необходимо найти
     * @return найденное значение или null, если совпадение не найдено
     */
    private T recursiveSearch(Node<T> node, T element) {
        if (node == null) {
            return null;
        }
        int compareToResult = element.compareTo(node.getData());

        if (compareToResult == 0) {
            return node.getData();
        }
        if (compareToResult < 0) {
            return recursiveSearch(node.getLeft(), element);
        } else {
            return recursiveSearch(node.getRight(), element);
        }
    }
}
