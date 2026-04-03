package ru.otus.java.basic.homeworx.homework_lesson_31;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MethodsForTestsTest {
    static MethodsForTests methods;

    @BeforeAll
    static void setUp() {
        methods = new MethodsForTests();
    }

    public static Stream<Arguments> arrStreamsWithoutExceptions() {
        List<Arguments> list = new ArrayList<>();
        list.add(Arguments.of(new int[]{1, 2, 1, 2, 2, 2}, new int[]{2, 2, 2}));
        list.add(Arguments.of(new int[]{1, 2}, new int[]{2}));
        list.add(Arguments.of(new int[]{2, 1}, new int[0]));
        list.add(Arguments.of(new int[]{1}, new int[0]));
        return list.stream();
    }

    @ParameterizedTest
    @MethodSource("arrStreamsWithoutExceptions")
    void getSubArrayWithoutExceptions(int[] arr, int[] res) {
        assertArrayEquals(res, methods.getSubArray(arr));
    }

    public static Stream<Arguments> arrStreamsWithExceptions() {
        return Stream.of(
                Arguments.of(new int[0]),
                Arguments.of(new int[]{2})
        );
    }

    @ParameterizedTest
    @MethodSource("arrStreamsWithExceptions")
    void getSubArrayWithExceptions(int[] arr) {
        assertThrows(RuntimeException.class, () -> methods.getSubArray(arr));
    }

    public static Stream<Arguments> checkArrayTrue() {
        return Stream.of(
                Arguments.of(new int[]{1, 2}),
                Arguments.of(new int[]{1, 2, 2, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("checkArrayTrue")
    void checkArray(int[] arr) {
        assertTrue(methods.checkArray(arr));
    }

    public static Stream<Arguments> checkArrayFalse() {
        return Stream.of(
                Arguments.of(new int[]{1}, false),
                Arguments.of(new int[0], false),
                Arguments.of(new int[]{1, 1}, false),
                Arguments.of(new int[]{1, 3}, false),
                Arguments.of(new int[]{1, 2, 2, 1, 0}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("checkArrayFalse")
    void checkArray(int[] arr, boolean expected) {
        assertEquals(expected, methods.checkArray(arr));
    }
}