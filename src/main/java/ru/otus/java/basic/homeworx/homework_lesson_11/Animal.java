package ru.otus.java.basic.homeworx.homework_lesson_11;

/**
 * Родительский класс животных
 * name - имя животного
 * runningSpeedMpS - скорость бега
 * stamina - выносливость животного
 * runStaminaPerMetre - затраты выносливости от бега (1 м\с для всех животных - устанавливается в конструкторе)
 */
public abstract class Animal {
    protected String name;
    protected int runningSpeedMpS;
    protected int stamina;
    protected int runStaminaPerMetre;

    public Animal(String name, int runningSpeedMpS, int stamina) {
        this.name = name;
        this.runningSpeedMpS = runningSpeedMpS;
        this.stamina = stamina;
        runStaminaPerMetre = 1;
    }

    /**
     * Метода для определения оставшегося уровня выносливости после активности (независимо от вида активности)
     * @apiNote не предусматривается восстановление уровня выносливости
     * @param distance - дистанция, на протяжении которой выполняется активность
     * @param staminaConsumption - затраты выносливости
     * @return оставшийся уровень активности
     */
    public int staminaState(int distance, int staminaConsumption){
        stamina = stamina - (distance * staminaConsumption);
        return stamina;
    }

    /**
     * Метод, имитирующий бег
     * @param distance - дистанция, на протяжении которой выполняется активность
     * @return - время, затраченное на преодолении дистанции (при недостаточности выносливости, возвращается "-1")
     */
    public int run(int distance){
        System.out.println(name + " побежал дистанцию: " + distance + " м.");
        if(staminaState(distance, runStaminaPerMetre) < 0){
            stamina = 0;
            return -1;
        }
        return distance/ runningSpeedMpS;
    }

    /**
     * Метод для вывода в консоль информации о времени, затраченном на выполнение активности, или сообщение о недостаточной выносливости
     * @param time - время, затраченное на преодолении дистанции (результат выполнения метода run(int distance))
     */
    public void printActionInfo(int time) {
        if(time <= 0) {
            System.out.println("У животного появилось состояние усталости");
        } else{
            System.out.println("За время: " + time + " с.");
        }
    }

    /**
     * Метод для вывода в консоль информации о животном
     */
    public void info(){
        System.out.println("name='" + name + '\'' +
                ", stamina=" + stamina +
                ", runningSpeed=" + runningSpeedMpS);
    }
}
