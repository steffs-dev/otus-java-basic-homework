package ru.otus.java.basic.homeworx.homework_lesson_18;

/**
 * Класс для тестирования работы дерева на классе с кастомным переопределением метода compareTo
 */
public class Employee implements Comparable<Employee>{
    private int id;
    private String name;
    private int salary;

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    /**
     * Переопределенный метод для сравнения объектов класса
     * @param o the object to be compared.
     * @return результат сравнения объектов (сравнение по id)
     */
    @Override
    public int compareTo(Employee o) {
        return this.id - o.id;
    }
}
