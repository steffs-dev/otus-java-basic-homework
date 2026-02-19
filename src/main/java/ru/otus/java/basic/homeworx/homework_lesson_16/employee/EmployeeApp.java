package ru.otus.java.basic.homeworx.homework_lesson_16.employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для запуска приложения
 */
public class EmployeeApp {
    public static void main(String[] args) {
        EmployeeHandler employeeHandler = new EmployeeHandler();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Petr", 22));
        employees.add(new Employee("Oleg", 67));
        employees.add(new Employee("Max", 54));
        employees.add(new Employee("Ivan", 18));
        employees.add(new Employee("Anna", 33));

        List<Employee> nullList = null;
        List<Employee> emptyList = new ArrayList();

        System.out.println(employeeHandler.names(employees));
        System.out.println(employeeHandler.filterByAge(employees, 33));
        System.out.println(employeeHandler.isAverageAgeAboveParam(
                employees, 33
        )); // средний возраст работников в employees = 38.8
        System.out.println(employeeHandler.findYoungest(employees));


    }
}
