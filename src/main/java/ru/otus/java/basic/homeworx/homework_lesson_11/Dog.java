package ru.otus.java.basic.homeworx.homework_lesson_11;

/**
 * Класс собак - потомок класса Animal
 * swimmingSpeedMpS - скорость плавания
 * swimStaminaPerMetre - затраты выносливости от плавания (2 м\с для собак - устанавливается в конструкторе)
 */
public class Dog extends Animal{
    private final int swimmingSpeedMpS;
    private final int swimStaminaPerMetre;
    public Dog(String name, int runningSpeedMpS, int swimmingSpeedMpS, int stamina) {
        super(name, runningSpeedMpS, stamina);
        this.swimmingSpeedMpS = swimmingSpeedMpS;
        swimStaminaPerMetre = 2;
    }

    /**
     * Метод, имитирующий плавание
     * @param distance - дистанция, на протяжении которой выполняется активность
     * @return - время, затраченное на преодолении дистанции (при недостаточности выносливости, возвращается "-1")
     */
    public int swim(int distance) {
        System.out.println(name + " поплыл дистанцию: " + distance + " м.");
        if(staminaState(distance, swimStaminaPerMetre) < 0){
            stamina = 0;
            return -1;
        }
        return distance/ swimmingSpeedMpS;
    }
}
