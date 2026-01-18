package ru.otus.java.basic.homeworx.homework_lesson_11;

/**
 * Класс лошадей - потомок класса Animal
 * swimmingSpeedMpS - скорость плавания
 * swimStaminaPerMetre - затраты выносливости от плавания (4 м\с для лошадей - устанавливается в конструкторе)
 */
public class Horse extends Animal{
    private final int swimmingSpeedMpS;
    private final int swimStaminaPerMetre;
    public Horse(String name, int runningSpeedMpS, int swimmingSpeedMpS, int stamina) {
        super(name, runningSpeedMpS, stamina);
        this.swimmingSpeedMpS = swimmingSpeedMpS;
        swimStaminaPerMetre = 4;
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
