package ru.otus.java.basic.homeworx.homework_lesson_27;

/**
 * Класс для запуска программы
 */
public class FruitBoxApp {
    public static void main(String[] args) {
        Box<Orange> orangeBox1 = new Box<>("Orange1", Orange.class);
        orangeBox1.add(new Orange(1));
        orangeBox1.add(new Orange(1));
        orangeBox1.printInfo();

        Box<Orange> orangeBox2 = new Box<>("Orange2", Orange.class);
        orangeBox2.add(new Orange(2));
        orangeBox2.printInfo();

        orangeBox1.printCompare(orangeBox2);

        orangeBox1.moveFruits(orangeBox2);
        orangeBox1.printInfo();
        orangeBox2.printInfo();
        orangeBox1.printCompare(orangeBox2);

        Box<Apple> appleBox1 = new Box<>("Apple1", Apple.class);
        appleBox1.add(new Apple(1));
        appleBox1.moveFruits(orangeBox2);
        appleBox1.printInfo();
        orangeBox2.printInfo();

        Box<Fruit> fruitBox = new Box<>("Fruit1", Fruit.class);
        fruitBox.add(new Apple(1));
        fruitBox.add(new Orange(2));
        fruitBox.printInfo();

        orangeBox2.moveFruits(fruitBox);
        fruitBox.printInfo();
        orangeBox2.printInfo();

        fruitBox.moveFruits(orangeBox1);
        fruitBox.printInfo();
        orangeBox1.printInfo();
    }
}
