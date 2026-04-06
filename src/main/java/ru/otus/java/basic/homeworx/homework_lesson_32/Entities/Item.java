package ru.otus.java.basic.homeworx.homework_lesson_32.Entities;

import java.util.Objects;

/**
 * Класс "Товар", соответствующий таблице items в БД.
 * Используется для хранения данных о товаре: идентификатор, наименование, цена.
 */
public class Item {
    private Long id;
    private String name;
    private Integer price;

    /**
     * Пустой конструктор (для Jackson и JPA).
     */
    public Item() {
    }

    /**
     * Конструктор для создания товара без указания ID (ID генерируется БД).
     *
     * @param name  наименование товара
     * @param price цена товара
     */
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Геттеры и сеттеры
     */
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
