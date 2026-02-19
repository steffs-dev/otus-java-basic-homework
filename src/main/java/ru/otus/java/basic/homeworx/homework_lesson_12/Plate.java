package ru.otus.java.basic.homeworx.homework_lesson_12;

/**
 * Класс тарелка
 * maxFoodLevel - максимальное количество еды, которое помещается в тарелку
 * currentFoodLevel - текущее количество еды в тарелке
 * threshold - пороговое значение, при котором тарелка заполняется едой из мешка - не предусмотрено заданием
 * foodBag - мешок, из которого накладывается еда - не предусмотрено заданием
 */
public class Plate {
    private final int maxFoodLevel;
    private int currentFoodLevel;
    private final int threshold;
    private final FoodBag foodBag;

    /**
     *
     * @param maxFoodLevel - максимальное количество еды, которое помещается в тарелку. 0, если число отрицательное
     * @param foodBagSize - максимальный размер мешка
     * по заданию при создании currentFoodLevel = maxFoodLevel. Изменил логику, т.к. наполнение происходит из
     * мешка, размер которого может быть меньше размера тарелки
     */
    public Plate(int maxFoodLevel, int foodBagSize) {
        this.maxFoodLevel = Math.max(maxFoodLevel, 0);
        threshold = maxFoodLevel/2;
        foodBag = new FoodBag(foodBagSize);
        addFood(foodBag);
    }

    @Override
    public String toString() {
        return "Plate:\n" +
                "* maxFoodLevel=" + maxFoodLevel + "\n" +
                "* currentFoodLevel=" + currentFoodLevel + "\n" +
                "* threshold=" + threshold + "\n" +
                "*** " + foodBag;
    }

    /**
     * Метод добавляет еду с тарелку при условии, что текущее количество еды в тарелке меньше порогового
     * @param foodBag - мешок, из которого тарелка наполняется едой
     */
    public void addFood(FoodBag foodBag){
        if(currentFoodLevel <= threshold){
            currentFoodLevel += foodBag.addToThePlate(maxFoodLevel-currentFoodLevel);
        }
    }

    /**
     * Метода, уменьшающий количество еды в тарелке
     * @param foodToReduce - количество еды, на которое предполагается уменьшить объем еды на тарелке
     * @return возвращает true, если currentFoodLevel достаточно для уменьшения на foodToReduce,
     * возвращает false, если еды недостаточно (по условию уменьшать currentFoodLevel можно только весь foodToReduce)
     */
    public boolean reduceFood(int foodToReduce){
        if(foodToReduce > currentFoodLevel){
            return false;
        }
        currentFoodLevel -= foodToReduce;
        addFood(foodBag);
        return true;
    }

    /**
     * Вывод в консоль информации о тарелке в формате переопределенного метода toString()
     */
    public void info() {
        System.out.println(this);
    }
}
