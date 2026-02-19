package ru.otus.java.basic.homeworx.homework_lesson_12;

/**
 * Класс котов.
 * name - имя кота
 * appetite - количество еды, необходимое для насыщения
 * satiety - сытость (сыт - true, голоден - false)
 */
public class Cat {
    private final String name;
    private final int appetite;
    private boolean satiety;

    /**
     * @param name     - имя кота
     * @param appetite - количество еды, необходимое для насыщения. 0, если число отрицательное
     *                 satiety - дефолтное значение сытости false
     */
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = Math.max(appetite, 0);
        this.satiety = false;
    }

    /**
     * Метод имитирующие потребление котом еды
     *
     * @param plate - класс тарелки, из которой кот потребляет пищу.
     *              Если кот смог потребить количество пищи, равное appetite, показатели сытости меняется на true
     */
    public void eat(Plate plate) {
        if (plate.reduceFood(appetite)) {
            satiety = true;
        }
    }

    /**
     * Вывод в консоль информации о коте
     */
    public void info() {
        System.out.println("Name: " + name + " Satiety: " + satiety + "\n------------------------");
    }

}
