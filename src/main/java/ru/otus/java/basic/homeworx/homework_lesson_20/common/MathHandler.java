package ru.otus.java.basic.homeworx.homework_lesson_20.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для проверки полученного примера заданному формату и выполнения математических действий
 * Шаблон: ЧИСЛО [знак математического действия] ЧИСЛО
 * Перед Числами может стоять минус, число может начинаться на 0 только если это 0 или оно дробное меньше 1. Дробная
 * часть может отделяться "." и ","
 * Перед и после знака математического действия допускаются пробелы
 * FIGURE_GROUP - группа регулярного выражения, задающая шаблон числа
 * ACTION_GROUP - группа регулярного выражения, задающая шаблон знака математического действия и его окружения
 * REGEX - полное регулярное выражение
 */
public final class MathHandler {
    private static final String FIGURE_GROUP = "(-?(?:(?:[1-9]\\d*(?:[.,]\\d+)?)|0(?:[.,]\\d+)?))";
    private static final String ACTION_GROUP = "([ ]*[-+*/][ ]*)";
    private static final String REGEX = "^" + FIGURE_GROUP + ACTION_GROUP + FIGURE_GROUP + "$";

    /**
     * Метод для проверки полученного примера, вычленения из прошедшей проверку строки первого числа, знака
     * математического действия и второго числа, возвращает строку - результат вычисления
     *
     * @param inputMessage - полученнная строка, которая должна содержать математический пример по заданному шаблону
     * @return строка - результат вычисления математического примера
     * @throws IllegalTaskFormatException - если inputMessage не соответствует шаблону
     */
    public static String generateResult(String inputMessage) throws IllegalTaskFormatException {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(inputMessage);

        if (!matcher.matches()) {
            throw new IllegalTaskFormatException(inputMessage);
        }
        double firstFigure = Double.parseDouble(matcher.group(1).replace(",", "."));
        String action = matcher.group(2).trim();
        double secondFigure = Double.parseDouble(matcher.group(3).replace(",", "."));

        return actionsSwitcher(action, firstFigure, secondFigure);
    }

    /**
     * Метод вызывает метод, соответствующий знаку математического действия
     *
     * @param action       - знак математического действия
     * @param firstFigure  - первое число
     * @param secondFigure - второе число
     * @return результат вычисления
     */
    private static String actionsSwitcher(String action, double firstFigure, double secondFigure) {
        String result = "none";
        switch (action) {
            case "+" -> {
                result = addition(firstFigure, secondFigure);
            }
            case "-" -> {
                result = subtraction(firstFigure, secondFigure);
            }
            case "*" -> {
                result = multiplication(firstFigure, secondFigure);
            }
            case "/" -> {
                result = division(firstFigure, secondFigure);
            }
        }
        return result;
    }

    /**
     * Метод вычисления примера на сложение
     *
     * @param firstFigure  - первое число
     * @param secondFigure - второе число
     * @return результат вычисления
     */
    private static String addition(double firstFigure, double secondFigure) {
        return String.valueOf(firstFigure + secondFigure);
    }

    /**
     * Метод вычисления примера на вычитание
     *
     * @param firstFigure  - первое число
     * @param secondFigure - второе число
     * @return результат вычисления
     */
    private static String subtraction(double firstFigure, double secondFigure) {
        return String.valueOf(firstFigure - secondFigure);
    }

    /**
     * Метод вычисления примера на умножение
     *
     * @param firstFigure  - первое число
     * @param secondFigure - второе число
     * @return результат вычисления
     */
    private static String multiplication(double firstFigure, double secondFigure) {
        return String.valueOf(firstFigure * secondFigure);
    }

    /**
     * Метод вычисления примера на деление
     *
     * @param firstFigure  - первое число
     * @param secondFigure - второе число
     * @return результат вычисления
     * @apiNote добавлена проверка деления на 0
     */
    private static String division(double firstFigure, double secondFigure) {
        if (secondFigure == 0) {
            return String.format("Нерешаемая задача (%f / %f), т.к. на 0 делить нельзя",
                    firstFigure, secondFigure);
        }
        return String.valueOf(firstFigure / secondFigure);
    }
}
