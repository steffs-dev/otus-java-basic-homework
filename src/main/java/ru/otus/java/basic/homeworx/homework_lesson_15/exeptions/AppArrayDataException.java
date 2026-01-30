package ru.otus.java.basic.homeworx.homework_lesson_15.exeptions;

import java.io.FileNotFoundException;

public class AppArraySizeException extends RuntimeException {
    public AppArraySizeException() {
        super();
    }

    public AppArraySizeException(String message) {
        super(message);
    }
    public AppArraySizeException(int requiredNumberOfRows, int factualNumberOfRows) {
        this(String.format("Inappropriate array format. Expected rows: %d. Provided rows: %d",
                requiredNumberOfRows, factualNumberOfRows));
    }
    public AppArraySizeException(int inTheRow, int requiredNumberOfColumns, int factualNumberOfColumns) {
        this(String.format("Inappropriate array format in the row %d. Expected rows: %d. Provided rows: %d",
                inTheRow, requiredNumberOfColumns, factualNumberOfColumns));
    }
}
