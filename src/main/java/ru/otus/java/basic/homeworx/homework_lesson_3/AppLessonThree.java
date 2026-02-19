package ru.otus.java.basic.homeworx.homework_lesson_3;

import java.util.Scanner;

public class AppLessonThree {
    public static void main(String[] args) {

        methodSwitcher();

//        greetings();
//        checkSign(1, 2, 0);
//        selectColor();
//        compareNumbers();
//        addOrSubtractAndPrint(1, 3, true);
    }

    /*(1) Реализуйте метод greetings(), который при вызове должен отпечатать в столбец 4 слова:
     Hello, World, from, Java
     */
    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from\nJava");
    }

    /*
    (2) Реализуйте метод checkSign(..), принимающий в качестве аргументов 3 int переменные
    a, b и c. Метод должен посчитать их сумму, и если она больше или равна 0, то вывести в
    консоль сообщение “Сумма положительная”, в противном случае - “Сумма отрицательная”
     */
    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        String message = (sum >= 0) ? "Сумма положительная" : "Сумма отрицательная";
        System.out.println(message);
    }

    /*(3) Реализуйте метод selectColor() в теле которого задайте int переменную data с
    любым начальным значением. Если data меньше 10 включительно, то в консоль должно быть
    выведено сообщение “Красный”, если от 10 до 20 включительно, то “Желтый”, если больше
    20 - “Зеленый”
     */
    public static void selectColor() {
        int data = 21;

        if (data <= 10) {
            System.out.println("Красный");
        } else if (data > 10 && data <= 20) {
            System.out.println("Желтый");
        } else if (data > 20) {
            System.out.println("Зеленый");
        }
    }

    /*(4) Реализуйте метод compareNumbers(), в теле которого объявите две int переменные
    a и b с любыми начальными значениями. Если a больше или равно b, то необходимо вывести
    в консоль сообщение “a >= b”, в противном случае “a < b”
     */
    public static void compareNumbers() {
        int a = 20;
        int b = 10;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    /*(5) Создайте метод addOrSubtractAndPrint(int initValue, int delta, boolean increment).
    Если increment = true, то метод должен к initValue прибавить delta и отпечатать в
    консоль результат, в противном случае - вычесть
     */
    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            initValue += delta;
            System.out.println(initValue);
        } else {
            initValue -= delta;
            System.out.println(initValue); //в задании нет про вывод в консоль
        }
    }

    /*(*) При запуске приложения, запросите у пользователя число от 1 до 5, и после ввода
    выполнения метод, соответствующий указанному номеру со случайным значением аргументов
     */
    public static void methodSwitcher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Укажите число от 1 до 5 включительно");
        int methodNumber = 0; //можно сделать boolean flag и поместить его в loop
        while (methodNumber < 1 || methodNumber > 5) {
            methodNumber = sc.nextInt();
            if (methodNumber >= 1 && methodNumber <= 5) {
                switch (methodNumber) {
                    case 1 -> greetings();
                    case 2 -> {
                        checkSign(1, 2, 0); // sum = 3
                        checkSign(3, 5, -8); // sum = 0
                        checkSign(-3, -6, 7); // sum = -2
                    }
                    case 3 -> selectColor();
                    case 4 -> compareNumbers();
                    case 5 -> {
                        addOrSubtractAndPrint(1, 3, true); // initValue = 4
                        addOrSubtractAndPrint(3, 2, false); // initValue = 1
                    }
                }
            } else {
                System.out.println("Введено неверное число: " + methodNumber +
                        ". Укажите число от 1 до 5 включительно");
            }
        }
    }
}
