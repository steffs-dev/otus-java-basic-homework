package ru.otus.java.basic.homeworx.homework_lesson_18;

import java.util.List;

/**
 * Класс для запуска приложения
 */
public class BinarySearchTreeApp {

    public static void main(String[] args) {
        BinarySearchTreeDataCreation data = new BinarySearchTreeDataCreation();

        List<Integer> intList = data.createRandomIntegerList(15, 1, 100);
        BinarySearchTree<Integer> bstInt = new BinarySearchTree<>(intList);
        System.out.println("Сформированный сортированный список: " + bstInt.getList());
        bstInt.printTree();
        System.out.println(bstInt.find(bstInt.getList().getFirst())); // значение точно будет найдено
        System.out.println("-".repeat(10));
        System.out.println(bstInt.find(110)); //значение должно превышать max в методе createRandomIntegerList, чтобы получить null
        System.out.println("-".repeat(10));

        List<String> strList = data.createRandomStringList(15, 'a', 'y');
        BinarySearchTree<String> bstStr = new BinarySearchTree<>(strList);
        System.out.println("Сформированный сортированный список: " + bstStr.getList());
        bstStr.printTree();
        System.out.println(bstStr.find(bstStr.getList().getFirst())); // значение точно будет найдено
        System.out.println("-".repeat(10));
        System.out.println(bstStr.find("z")); //значение должно превышать max в методе createRandomStringList, чтобы получить null
        System.out.println("-".repeat(10));

        List<Employee> empList = data.createRandomEmployeesList(7);
        BinarySearchTree<Employee> bstEmp = new BinarySearchTree<>(empList);
        List<Employee> sortedList = bstEmp.getList();
        System.out.println("Сформированный сортированный список: " + bstEmp.getList());
        bstEmp.printTree();
        System.out.println(bstEmp.find(sortedList.getFirst())); // значение точно будет найдено
        System.out.println("-".repeat(10));
        Employee employee = sortedList.getLast();
        System.out.println(bstEmp.find(new Employee(employee.getId() + 1, employee.getName(), employee.getSalary()))); //значение должно превышать max в методе createRandomEmployeesList, чтобы получить null
        System.out.println("-".repeat(10));
    }
}
