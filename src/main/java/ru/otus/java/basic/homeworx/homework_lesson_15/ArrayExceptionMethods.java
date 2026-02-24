package ru.otus.java.basic.homeworx.homework_lesson_15;

import ru.otus.java.basic.homeworx.homework_lesson_15.exeptions.AppArraySizeException;
import ru.otus.java.basic.homeworx.homework_lesson_15.exeptions.AppArrayDataException;

/**
 * Класс с методами для работы с массивом
 */
public class ArrayExceptionMethods {

    /**
     * Метод для проверки валидности полученного массива.
     *
     * @param stringArray - двумерный массив
     * @apiNote - добавлена проверка соответствие размера массива (всего массива и отдельных строк),
     * проверка на нал всего массива и срок, при несоответствии выбрасывается AppArraySizeException
     */
    public void arraySizeValidation(String[][] stringArray) {
        int requiredNumberOfRows = 4;
        int requiredNumberOfColumns = 4;

        if (stringArray == null || stringArray.length != requiredNumberOfRows) {
            throw new AppArraySizeException(requiredNumberOfRows,
                    (stringArray == null) ? 0 : stringArray.length);
        }
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i] == null || stringArray[i].length != requiredNumberOfColumns) {
                throw new AppArraySizeException(i, requiredNumberOfColumns,
                        (stringArray[i] == null) ? 0 : stringArray[i].length);
            }
        }
    }

    /**
     * Метод для парсинга строкового массива в целочисленный.
     * Метод исполняется при условии успешного завершения работы метода arraySizeValidation().
     *
     * @param stringArray - двумерный строковой массив
     * @return - целочисленный двумерный массив
     * @apiNote если символ в ячейке массива не может быть распарсен в число, выбрасывается AppArrayDataException
     */
    public int[][] parseStringArrayToInt(String[][] stringArray) {
        arraySizeValidation(stringArray);
        int[][] intArray = new int[stringArray.length][stringArray[0].length];
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                try {
                    intArray[i][j] = Integer.parseInt(stringArray[i][j]);
                } catch (IllegalArgumentException e) {
                    throw new AppArrayDataException(stringArray[i][j], i, j);
                }
            }
        }
        return intArray;
    }

    /**
     * Метод подсчитывает и выводит в консоль сумму всех элементов массива
     *
     * @param intArray - целочисленный двумерный массив
     */
    public void printSumArrayCells(int[][] intArray) {
        int sum = 0;
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i].length; j++) {
                sum += intArray[i][j];
            }
        }
        System.out.println("Sum of the array elements = " + sum);
    }
}

