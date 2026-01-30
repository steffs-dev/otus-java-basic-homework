package ru.otus.java.basic.homeworx.homework_lesson_15.exeptions;

/**
 * Unchecked exception, который может быть выброшен при несоответствии массива заданному размеру
 */
public class AppArraySizeException extends RuntimeException {
    public AppArraySizeException() {
        super();
    }

    /**
     * Конструктор, передающий в строку в конструктор супер-класса
     *
     * @param message - текст, выводимый в консоль при выбросе исключения
     */
    public AppArraySizeException(String message) {
        super(message);
    }

    /**
     * Конструктор для формирования сообщения об ошибке в количестве срок двумерного массива с
     * дополнительными параметрами
     *
     * @param requiredNumberOfRows - целевое количество строк в массиве
     * @param factualNumberOfRows  - фактическое количество строк в массиве
     */
    public AppArraySizeException(int requiredNumberOfRows, int factualNumberOfRows) {
        this(String.format("Inappropriate array size. Expected rows: %d. Provided rows: %d\n",
                requiredNumberOfRows, factualNumberOfRows));
    }

    /**
     * Конструктор для формирования сообщения об ошибке в количестве колонок с дополнительными параметрами
     *
     * @param inTheRow                - строка двумерного массива, в которой найдено несоответствие между целевой и
     *                                фактической длиной одномерного массива, входящего в двумерный
     * @param requiredNumberOfColumns - целевая длина одномерного массива
     * @param factualNumberOfColumns  - фактическая длина одномерного массива
     */
    public AppArraySizeException(int inTheRow, int requiredNumberOfColumns, int factualNumberOfColumns) {
        this(String.format("Inappropriate array size in the row %d. Expected columns: %d. " +
                        "Provided columns: %d\n",
                inTheRow, requiredNumberOfColumns, factualNumberOfColumns));
    }
}
