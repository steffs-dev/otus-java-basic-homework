package ru.otus.java.basic.homeworx.homework_lesson_19;

import java.nio.file.Path;

/**
 * Исключение, выбрасываемое в случае, если в выбранной директории отсутствуют файлы заданного типа
 */
public class EmptyDirectoryException extends Exception {

    public EmptyDirectoryException(String dirName, String type){
        super(String.format("В папке %s отсутствуют файлы %s", dirName, type));
    }

    public EmptyDirectoryException(Path dirName, String type){
        super(String.format("В папке %s отсутствуют файлы %s", dirName.getFileName(), type));
    }
}
