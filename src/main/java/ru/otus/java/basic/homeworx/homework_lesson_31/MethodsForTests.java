package ru.otus.java.basic.homeworx.homework_lesson_31;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MethodsForTests {

    private final static Logger logger = LogManager.getLogger(MethodsForTests.class.getName());

    public int[] getSubArray(int[] arr) {
        if (arr.length == 0) {
            logger.error("Empty array");
            throw new RuntimeException();
        }

        int positionFound = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                positionFound = i;
                break;
            }
        }

        if (positionFound == -1) {
            logger.warn("Target figure was not found");
            throw new RuntimeException();
        }

        if (positionFound == arr.length - 1) {
            return new int[0];
        }

        int[] result = new int[arr.length - positionFound - 1];
        System.arraycopy(arr, positionFound + 1, result, 0, result.length);
        return result;
    }


    public boolean checkArray(int[] arr) {
        boolean containsOne = false;
        boolean containsTwo = false;
        if (arr.length <= 1) return false;

        for (int num : arr) {
            if (num == 1) {
                containsOne = true;
            } else if (num == 2) {
                containsTwo = true;
            } else {
                logger.debug("Inappropriate element in the array: {}", num);
                return false;
            }
        }
        return containsOne && containsTwo;
    }
}
