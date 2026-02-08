package ru.otus.java.basic.homeworx.homework_lesson_18;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс для заполнения списков для тестирования работы дерева
 */
public class BinarySearchTreeDataCreation {
    Random random;

    public BinarySearchTreeDataCreation() {
        random = new Random();
    }

    /**
     * Метод для создания не сортированного списка со случайными значениями Integer.
     * Метод compareTo в классе Integer переопределен (естественная сортировка).
     * @apiNote все элементы списка будут уникальными
     * @param size - количество элементов в списке
     * @param min - начальное значение (включительно) для составления списка
     * @param max - конечное значение (не включительно) для составления списка
     * @return список Integer
     */
    public List<Integer> createRandomIntegerList(int size, int min, int max) {
        return random.ints(min, max)
                .distinct()
                .limit(size)
                .boxed().collect(Collectors.toList());
    }

    /**
     * Метод для создания не сортированного списка со случайными значениями String, формируемыми из букв алфавита.
     * Метод compareTo в классе String переопределен (естественная сортировка)
     * @apiNote все элементы списка будут уникальными
     * @param size - количество элементов в списке
     * @param start - начальное значение (включительно) для составления списка
     * @param end - конечное значение (не включительно) для составления списка
     * @return список String
     */
    public List<String> createRandomStringList(int size, char start, char end) {
        return random.ints(start, end)
                .mapToObj(c -> String.valueOf((char) c))
                .distinct()
                .limit(size)
                .collect(Collectors.toList());
    }

    /**
     * Метод для создания не сортированного списка со случайными значениями объектов класса Employee.
     * Метод compareTo в классе Employee переопределен (сортировка по id)
     * @apiNote поскольку в классе Employee сортировка по id, для уникальности элементов списка значение id
     * формируется увеличением начального значения на 1
     * @param size - количество элементов в списке
     * @return список Employee
     */
    public List<Employee> createRandomEmployeesList(int size) {
        String[] firstnames = {"Иван", "Петр", "Сергей", "Федор", "Антон"};
        return IntStream.range(0, size)
                .mapToObj(i -> {
                            int id = 100 + i;
                            String name = firstnames[random.nextInt(firstnames.length)];
                            int salary = random.nextInt(100000) + 20000;
                            return new Employee(id, name, salary);
                        }
                )
                .collect(Collectors.toList());
    }
}
