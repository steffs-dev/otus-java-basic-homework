package ru.otus.java.basic.homeworx.homework_lesson_15;

import ru.otus.java.basic.homeworx.homework_lesson_15.exeptions.AppArrayDataException;
import ru.otus.java.basic.homeworx.homework_lesson_15.exeptions.AppArraySizeException;

public class ExceptionsApp {

    /**
     * Метод для проверки валидности полученного массива.
     *
     * @param stringArray
     * @return
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
                throw new AppArraySizeException(i + 1, requiredNumberOfColumns,
                        (stringArray[i] == null) ? 0 : stringArray.length);
            }
        }

    }

    public int[][] parseStringArrayToInt(String[][] stringArray) {
        arraySizeValidation(stringArray);
        int[][] intArray = new int[stringArray.length][stringArray[0].length];
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                try {
                    intArray[i][j] = Integer.parseInt(stringArray[i][j]);
                } catch (IllegalArgumentException e) {
                    throw new AppArrayDataException(i, j);
                }
            }
        }
        return intArray;
    }

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
