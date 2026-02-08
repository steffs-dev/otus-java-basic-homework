package ru.otus.java.basic.homeworx.homework_lesson_18;

/**
 * Узел дерева
 * @param <T> тип данных. Класс должен имплементировать интерфейс Comparable
 * data - данные, хранящиеся в узле
 * left - ссылка на узел слева
 * right - ссылка на узел справа
 */
public class Node<T extends Comparable<T>> {
    private final T data;
    private Node<T> left, right;

    public Node(T data) {
        this.data = data;
        left = right = null;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
