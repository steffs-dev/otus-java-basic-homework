package ru.otus.java.basic.homeworx.homework_lesson_5;

import java.util.Arrays;

public class AppLessonFive {

    public static void main(String[] args) {
        printLineNTimes(3, "privet");
        sumOfArrayElements(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(fillArrayWithNumber(9, new int[6])));
        System.out.println(Arrays.toString(increaseArrayElementsByNumber
                (3, new int[]{1, 2, 3, 4, 5})));
        sumsOfArrayHalvesComparator(new int[]{1, 2, 3, 4, 5, 6});
        sumsOfArrayHalvesComparator(new int[]{1, 9, 3, 4, 5});


    }

    /*Реализуйте метод, принимающий в качестве аргументов целое число и строку,
    и печатающий в консоль строку указанное количество раз
     */
    public static void printLineNTimes(int times, String line) {
        for (int i = times; i > 0; i--) {
            System.out.println(line);
        }
    }

    /*Реализуйте метод, принимающий в качестве аргумента целочисленный массив, суммирующий
    все элементы, значение которых больше 5, и печатающий полученную сумму в консоль.
     */
    public static void sumOfArrayElements(int[] array) {
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        System.out.println(sum);
    }

    /*Реализуйте метод, принимающий в качестве аргументов целое число и ссылку на целочисленный массив,
    метод должен заполниться каждую ячейку массива указанным числом
     */
    public static int[] fillArrayWithNumber(int number, int[] array) {
        Arrays.fill(array, number);

        // с использованием fori
//        for (int i = 0; i < array.length; i++) {
//            array[i] = number;
//        }
        return array;
    }

    /*Реализуйте метод, принимающий в качестве аргументов целое число и ссылку на целочисленный
    массив, увеличивающий каждый элемент которого на указанное число
     */
    public static int[] increaseArrayElementsByNumber(int number, int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] += number;
        }
        return array;
    }

    /*Реализуйте метод, принимающий в качестве аргумента целочисленный массив, и печатающий в консоль
    сумма элементов какой из половин массива больше
     */
    public static void sumsOfArrayHalvesComparator(int[] array) {
        int sumOfLeftHalf = 0, sumOfRightHalf = 0;
        int mid = (array.length / 2);
        int rightHalfStartIndex;

        if (array.length % 2 == 0) {
            rightHalfStartIndex = mid;
        } else {
            rightHalfStartIndex = mid + 1; //пропуск среднего элемента массива при нечетном количестве элементов
        }

        for (int i = 0; i < array.length; i++) {
            if (i < mid) {
                sumOfLeftHalf += array[i];
            } else if (i >= rightHalfStartIndex) {
                sumOfRightHalf += array[i];
            }
        }
        String message = (sumOfLeftHalf > sumOfRightHalf) ? "Левая половина массива больше"
                : "Правая половина массива больше";
        System.out.println(message);
    }

}
