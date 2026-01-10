package ru.otus.java.basic.homeworx.homework_lesson_10;

import java.util.Scanner;

/**
 * Класс Коробка
 * size - размер коробки (неизменяемый аттрибут)
 * colour - цвет коробки
 * itemInside - предмет, положенный в коробку, при отсутствии = null
 * isBoxOpened - флаг, показывающий открыта коробка (true) или нет (false)
 * isBoxEmpty - флаг, показывающий пуста коробка (true) или нет (false)
 * isWorkDone - флаг, показывающий завершена работа с коробкой (true) или нет (false)
 * scanner - дла работы с коробкой через консоль
 */
public class Box implements Messages {
    private final int size;
    private String colour;
    private String itemInside;
    private boolean isBoxOpened;
    private boolean isBoxEmpty;
    private boolean isWorkDone;
    Scanner scanner;

    public Box(int size, String colour) {
        this.size = size;
        this.colour = colour;
        itemInside = "none";
        isBoxOpened = false;
        isBoxEmpty = true;
        isWorkDone = false;
        scanner = new Scanner(System.in);
        System.out.println(welcomeMessage);
        System.out.println(listOfCommands);
        handler(); // при создании объекта, запускается работа с ним
    }

    /**
     * Метод меняет цвет коробки, выводит в консоль сообщение об изменении цвета
     *
     * @param colour новый цвет коробки
     */
    public void setColour(String colour) {
        this.colour = colour;
        String message = String.format(setColour, colour);
        System.out.println(message);
    }

    /**
     * Метод имитирует открытие коробки, выводя в консоль соответствующее сообщение
     */
    public void openBox() {
        isBoxOpened = true;
        System.out.println(boxOpened);
    }

    /**
     * Метод имитирует закрытие коробки, выводя в консоль соответствующее сообщение
     */
    public void closeBox() {
        isBoxOpened = false;
        System.out.println(boxClosed);
    }

    /**
     * Метод выводит в консоль информацию о коробке
     */
    public void getBoxInformation() {
        System.out.printf(boxInfo,
                size,
                colour,
                itemInside,
                isBoxOpened,
                isBoxEmpty);
    }

    /**
     * Метод имитирует помещение предмета в коробку
     *
     * @param item - предмет, помещаемый в коробку
     * @apiNote поместить предмет в коробку возможно только если она пуста и открыта.
     * При выполнении метода выводится сообщение о помещенном в коробку предмете и коробка закрывается
     */
    public void putItemToBox(String item) {
        if (!isBoxEmpty && !isBoxOpened) {
            System.out.println(boxIsFullAndClosedWarn);
            return;
        } else if (!isBoxEmpty) {
            System.out.println(boxIsFullWarn);
            return;
        } else if (!isBoxOpened) {
            System.out.println(boxIsClosedWarn);
            return;
        }
        itemInside = item;
        isBoxEmpty = false;
        String message = String.format(itemPlacedInBox, item);
        System.out.println(message);
        closeBox();
    }

    /**
     * Метода имитирует извлечение предмета из коробки
     *
     * @apiNote извлечь предмет из коробки возможно только если она не пуста и открыта.
     * При выполнении метода выводится сообщение об извлеченном предмете и коробка закрывается
     */
    public void removeItemFromBox() {
        if (isBoxEmpty && !isBoxOpened) {
            System.out.println(boxIsEmptyAndClosedWarn);
            return;
        } else if (isBoxEmpty) {
            System.out.println(boxIsEmptyWarn);
            return;
        } else if (!isBoxOpened) {
            System.out.println(boxIsClosedWarn);
            return;
        }
        String message = String.format(itemRemovedFromBox, itemInside);
        System.out.println(message);
        itemInside = "none";
        isBoxEmpty = true;
        closeBox();
    }

    /**
     * Метод выводит в консоль список команд для работы с коробкой
     */
    public void helpCommand() {
        System.out.println(listOfCommands);
    }

    /**
     * Метод читает введенный пользователем текст и обрабатывает команды
     */
    public void handler() {
        while (!isWorkDone) {
            System.out.print(enterCommand);
            String command = scanner.nextLine().toLowerCase().trim();
            String commandForSwitch = command.contains("_") ? command.substring(0, command.indexOf('_'))
                    : command;

            switch (commandForSwitch) {
                case "open" -> openBox();
                case "close" -> closeBox();
                case "info" -> getBoxInformation();
                case "take" -> removeItemFromBox();
                case "stop" -> stopWorking();
                case "help" -> helpCommand();
                case "colour" -> {
                    String colour = command.substring(command.indexOf('_') + 1);
                    setColour(colour);
                }
                case "put" -> {
                    String item = command.substring(command.indexOf('_') + 1);
                    putItemToBox(item);
                }
                default -> System.out.println(unknownCommand);
            }
        }
    }

    /**
     * Метод завершает работу scanner и работу программы
     */
    public void stopWorking() {
        isWorkDone = true;
        scanner.close();
        System.out.println(stopWorking);
    }

    public static void main(String[] args) {
        Box box = new Box(10, "red");
    }
}
