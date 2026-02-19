package ru.otus.java.basic.homeworx.homework_lesson_12;

/**
 * Класс мешка для пополнения тарелки
 * size - размер мешка
 * currentLevel - текущее количество еды в мешке
 */
public class FoodBag {
    private final int size;
    private int currentLevel;

    /**
     *
     * @param size - размер мешка. 0, если число отрицательное
     * при создании мешка он заполняется полностью
     */
    public FoodBag(int size) {
        this.size = Math.max(size, 0);
        currentLevel = this.size;
    }

    @Override
    public String toString() {
        return "FoodBag: " + "\n" +
                "*** size=" + size + "\n" +
                "*** currentLevel=" + currentLevel + "\n";
    }

    /**
     * Метод для наполнения тарелки
     * @param requestedFood - запрашиваемое количество еды для добавления в тарелку
     * @return количество еды, которое может быть добавлено в тарелку, исходя из остатков еды в мешке
     */
    public int addToThePlate(int requestedFood){
        if(requestedFood >= currentLevel){
            requestedFood = currentLevel;
            currentLevel = 0;
            return requestedFood;
        }
        currentLevel -= requestedFood;
        return requestedFood;
    }
}
