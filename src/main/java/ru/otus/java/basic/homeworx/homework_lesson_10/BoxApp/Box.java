package ru.otus.java.basic.homeworx.homework_lesson_10.BoxApp;

/**
 * Класс Коробка
 * size - размер коробки (неизменяемый аттрибут)
 * colour - цвет коробки
 * itemInside - предмет, положенный в коробку, при отсутствии = null
 * isOpened - флаг, показывающий открыта коробка (true) или нет (false)
 * isEmpty - флаг, показывающий пуста коробка (true) или нет (false)
 */
public class Box {
    private final int size;
    private String colour;
    private String itemInside;
    private boolean isOpened;
    private boolean isEmpty;

    public Box(int size, String colour) {
        this.size = size;
        this.colour = colour;
        itemInside = "none";
        isOpened = false;
        isEmpty = true;
        System.out.println(Messages.onBoxCreationMessage);
    }

    /**
     * Метод меняет цвет коробки, выводит в консоль сообщение об изменении цвета
     * @apiNote добавлена проверка на null и на отсутствие текста
     * @param colour новый цвет коробки
     */
    public void setColour(String colour) {
        if (colour == null || colour.isBlank()) {
            System.out.printf(Messages.incorrectValueWarn, Messages.colourRequirements);
            return;
        }
        this.colour = colour;
        System.out.printf(Messages.setColour, colour);
    }

    /**
     * Метод имитирует открытие коробки, выводя в консоль соответствующее сообщение
     */
    public void open() {
        isOpened = true;
        System.out.println(Messages.boxOpened);
    }

    /**
     * Метод имитирует закрытие коробки, выводя в консоль соответствующее сообщение
     */
    public void close() {
        isOpened = false;
        System.out.println(Messages.boxClosed);
    }

    /**
     * Метод выводит в консоль информацию о коробке
     */
    public void getInformation() {
        System.out.printf(Messages.boxInfo,
                size,
                colour,
                itemInside,
                isOpened,
                isEmpty);
    }

    /**
     * Метод имитирует помещение предмета в коробку
     *
     * @param item - предмет, помещаемый в коробку
     * @apiNote поместить предмет в коробку возможно только если она пуста и открыта.
     * При выполнении метода выводится сообщение о помещенном в коробку предмете и коробка закрывается
     */
    public void placeItem(String item) {
        if (!isEmpty && !isOpened) {
            System.out.println(Messages.boxIsFullAndClosedWarn);
            return;
        }
        if (!isEmpty) {
            System.out.println(Messages.boxIsFullWarn);
            return;
        }
        if (!isOpened) {
            System.out.println(Messages.boxIsClosedWarn);
            return;
        }
        itemInside = item;
        isEmpty = false;
        String message = String.format(Messages.itemPlacedInBox, item);
        System.out.println(message);
        close();
    }

    /**
     * Метода имитирует извлечение предмета из коробки
     *
     * @apiNote извлечь предмет из коробки возможно только если она не пуста и открыта.
     * При выполнении метода выводится сообщение об извлеченном предмете и коробка закрывается
     */
    public void removeItem() {
        if (isEmpty && !isOpened) {
            System.out.println(Messages.boxIsEmptyAndClosedWarn);
            return;
        } else if (isEmpty) {
            System.out.println(Messages.boxIsEmptyWarn);
            return;
        } else if (!isOpened) {
            System.out.println(Messages.boxIsClosedWarn);
            return;
        }
        String message = String.format(Messages.itemRemovedFromBox, itemInside);
        System.out.println(message);
        itemInside = "none";
        isEmpty = true;
        close();
    }
}
