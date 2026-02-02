package ru.otus.java.basic.homeworx.homework_lesson_16.employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeHandler {

    /**
     * Метод для извлечения списка имен из списка работников
     *
     * @param employees - список работников
     * @throws IllegalArgumentException - если список null или пустой
     * @return список имен из представленного списка работников
     */
    public List<String> names(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Список должен не быть null или пустым");
        }
        // через цикл
        List<String> names = new ArrayList<>();
        for (Employee employee : employees) {
            names.add(employee.getName());
        }
        return names;
        // через стрим
//        return employees.stream().map(Employee::getName).collect(Collectors.toList());
    }

    /**
     * Метода для фильтрации сотрудников по возрасту
     *
     * @param employees - список работников
     * @param minAge    - возраст, ниже которого работники не попадают в выборку
     * @throws IllegalArgumentException - если список null или пустой
     * @return - отфильтрованный список работников
     */
    public List<Employee> filterByAge(List<Employee> employees, int minAge) {
        if (minAge < 0) {
            throw new IllegalArgumentException("Минимальный возраст не может быть отрицательным");
        }
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Список должен не быть null или пустым");
        }
        // через цикл
//        List<Employee> filteredEmployees = new ArrayList<>();
//        for (Employee employee : employees) {
//            if (employee.getAge() >= minAge) {
//                filteredEmployees.add(employee);
//            }
//        }
//        return filteredEmployees;

        // через стрим
        return employees.stream().filter(i -> i.getAge() >= minAge).collect(Collectors.toList());
    }

    /**
     * Метод для определения соответствия среднего возраста из списка и
     * заданного значения
     *
     * @param employees     - список работников
     * @param minAverageAge - заданный минимальный средний возраст
     * @throws IllegalArgumentException - если minAverageAge < 0, список null или пустой
     * @return true (если средний возраст работников из списка больше заданного),
     * false (если меньше или равен заданному)
     */
    public boolean isAverageAgeAboveParam(List<Employee> employees, double minAverageAge) {
        if (minAverageAge < 0) {
            throw new IllegalArgumentException("Средний возраст не может быть отрицательным");
        }
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Список должен не быть null или пустым");
        }

        //через цикл
        int sumOfAges = 0;
        for (Employee employee : employees) {
            sumOfAges += employee.getAge();
        }
        double averageAge = (double) sumOfAges / employees.size();
        return averageAge > minAverageAge;

        //через стрим
//        return employees.stream().mapToInt(Employee::getAge).average().getAsDouble() > minAverageAge;
    }

    /**
     * Метод для поиска самого молодого сотрудника из списка
     *
     * @param employees - список работников
     * @throws IllegalArgumentException - если список null или пустой
     * @return самый молодой сотрудник
     * (при совпадении возрастов - первый в списке с минимальным возрастом)
     */
    public Employee findYoungest(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Список должен не быть null или пустым");
        }

        //через итератор
        Employee youngestEmployee = null;
        ListIterator<Employee> listIterator = employees.listIterator();
        if (listIterator.hasNext()) {
            youngestEmployee = listIterator.next();
        }
        while (listIterator.hasNext()) {
            Employee next = listIterator.next();
            if (next.getAge() < youngestEmployee.getAge()) {
                youngestEmployee = next;
            }
        }
        return youngestEmployee;

        //через сортировку
//        List<Employee> tempList = new ArrayList<>();
//        tempList.addAll(employees); // чтобы не изменять employees
//        Collections.sort(tempList, new Comparator<Employee>() {
//
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                return o1.getAge() - o2.getAge();
//            }
//        });
//        return tempList.getFirst();

        // через стрим
//        return employees.stream().min(Comparator.comparing(Employee::getAge)).get();
    }
}
