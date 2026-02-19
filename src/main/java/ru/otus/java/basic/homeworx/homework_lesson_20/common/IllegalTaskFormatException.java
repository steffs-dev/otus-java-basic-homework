package ru.otus.java.basic.homeworx.homework_lesson_20.common;

/**
 * Класс исключения, выбрасываемого при несоответствии полученного примера заданному формату
 */
public class IllegalTaskFormatException extends Exception{
    public IllegalTaskFormatException(String inputMessage){
        super(String.format("Неверный формат выражения (%s). Пример: 7 + 6, 5.3*4, 5- 12, 18 /6.0", inputMessage));
    }
}
