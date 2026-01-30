package ru.otus.java.basic.homeworx.homework_lesson_15.exeptions;

/**
 * Unchecked exception, который может быть выброшен при невозможности конвертировать содержащуюся в
 * массиве строку в число
 */
public class AppArrayDataException extends IllegalArgumentException {
    public AppArrayDataException() {
        super();
    }

    /**
     * Конструктор, передающий в строку в конструктор супер-класса
     *
     * @param message - текст, выводимый в консоль при выбросе исключения
     */
    public AppArrayDataException(String message) {
        super(message);
    }

    /**
     * Конструктор для формирования сообщения об ошибке с дополнительными параметрами
     *
     * @param s          - строка, при конвертации которой возникла ошибка
     * @param row,column - адрес ячейки, в которой находилась строка, при конвертации которой возникла ошибка
     */
    public AppArrayDataException(String s, int row, int column) {
        this(String.format("Input \"%s\" in the cell (row %d column %d) cannot be parsed in a number\n",
                s, row, column));
    }
}
