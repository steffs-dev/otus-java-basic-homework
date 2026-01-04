package ru.otus.java.basic.homeworx.homework_lesson_7;


public class AppLessonSeven {

    public static void main(String[] args) {

//        int[][] array1 = new int[][]{
//                {-1, 9, 1},
//                {2, 3, 4},
//                {5, 6, 0}};

//        int[][] array2 = new int[3][];
//        array2[0] = new int[]{};
//        array2[1] = new int[]{};
//        array2[2] = new int[]{};

//        int[][] array3 = new int[3][];
//        array3[0] = new int[]{1, 2, 3};
//        array3[2] = new int[]{4, 5, 6};
//
//        int[][] array4 = new int[3][];
//        array4[0] = new int[]{-1, -2, -3};
//        array4[1] = new int[]{-4, -5, -6};
//        array4[2] = new int[]{-7, -8, -9};

//        System.out.println("sumOfPositiveElements(array) = " + sumOfPositiveElements(array2));
//        printSquare(6);
//        zeroDiagonals(array1);
//        System.out.println("findMax(array) = " + findMax(array3));
//        System.out.println("sumOfSecondRowElements(array) = " + sumOfSecondRowElements(array2));
    }

    /*Реализовать метод sumOfPositiveElements(..), принимающий в качестве аргумента целочисленный
    двумерный массив, метод должен посчитать и вернуть сумму всех элементов массива, которые больше 0;
     */

    /**
     * Метод считает и возвращает сумму всех элементов массива, которые больше 0
     *
     * @param array - двухмерный массив, внутри которого могут быть пустые или null одномерные массивы
     * @return возвращает int. Возвращает 0, если все одномерные массивы внутри array null и/или пустые
     * @apiNote добавлена проверка на not null внутри двухмерного массива
     */
    public static int sumOfPositiveElements(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > 0) {
                    sum += array[i][j];
                }
            }
        }
        return sum;
    }

    /*Реализовать метод, который принимает в качестве аргумента int size и печатает в консоль квадрат
    из символов * со сторонами соответствующей длины;
     */

    /**
     * Метод печатает в консоль квадрат из символов * со сторонами = size
     *
     * @param size - любой int
     * @apiNote добавлена проверка, чтобы size было > 0, иначе завершение метода и вывод в консоль сообщения
     */
    public static void printSquare(int size) {
        if (size <= 0) {
            System.out.println("Число должно быть больше 0");
            return;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    /*Реализовать метод, принимающий в качестве аргумента двумерный целочисленный массив,
    и зануляющий его диагональные элементы (можете выбрать любую из диагоналей, или занулить обе);
     */

    /**
     * Метод зануляет диагонали полученного двухмерного массива
     *
     * @param array - двухмерный массив, внутри которого могут быть пустые или null одномерные массивы
     * @apiNote добавлена проверка на not null и пустые массивы внутри двухмерного массива.
     * Предполагается, что в метод передается квадратная матрица.
     */
    public static void zeroDiagonals(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length == 0) {
                continue;
            }
            int backwardCounter = array.length - 1 - i;
            array[i][i] = 0;
            array[i][backwardCounter] = 0;
        }
        printArray(array);
    }

    /**
     * Внутренний метод для вывода в консоль двухмерного массива
     *
     * @param array - двухмерный массив int
     */
    private static void printArray(int[][] array) {
        for (int[] row : array) {
            if (row == null) {
                continue;
            }
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    //Реализовать метод findMax(int[][] array) который должен найти и вернуть максимальный элемент массива;

    /**
     * Метод находит и возвращает максимальный элемент массива
     *
     * @param array - двухмерный массив, внутри которого могут быть пустые или null одномерные массивы
     * @return возвращает int. Возвращает 0, если все одномерные массивы внутри array null и/или пустые
     * @apiNote добавлена проверка на not null и пустые массивы внутри двухмерного массива.
     */
    public static int findMax(int[][] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].length > 0) {
                max = array[i][0];
                break;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                continue;
            }
            for (int j = 0; j < array[i].length; j++) {
                //max = Math.max(max, array[i][j]);
                max = (array[i][j] > max) ? array[i][j] : max;
            }
        }
        return max;
    }

    /* Реализуйте метод, который считает сумму элементов второй строки двумерного массива,
    если второй строки не существует, то в качестве результата необходимо вернуть -1
     */

    /**
     * Метод считает сумму элементов второй строки двумерного массива.
     *
     * @param array - двухмерный массив, внутри которого могут быть пустые или null одномерные массивы
     *              result
     * @return возвращает int. Возвращает -1, если заданной строки нет или все одномерные массивы
     * внутри array null и/или пустые
     * @apiNote добавлена проверка на not null и пустые массивы внутри двухмерного массива.
     */
    public static int sumOfSecondRowElements(int[][] array) {
        int numberOfRowToSum = 1; //номер строки массива для суммирования
        if (array.length < 2 || array[numberOfRowToSum] == null) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < array[numberOfRowToSum].length; i++) {
            result += array[numberOfRowToSum][i];
        }
        return result;
    }
}
