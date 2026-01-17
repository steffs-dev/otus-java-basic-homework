package ru.otus.java.basic.homeworx.homework_lesson_10.BoxApp;

import java.util.Scanner;

/**
 * Класс, предназначенный для взаимодействия пользователя с коробкой
 * box - экземпляр класса Box, в отношении которого осуществляется взаимодействие
 * boxSize - размер коробки
 * boxColour - цвет коробки
 * isWorkDone - флаг, показывающий завершена работа с коробкой (true) или нет (false)
 * scanner - дла работы с коробкой через консоль
 */
public class BoxHandler {
    private Box box;
    private int boxSize; //тут, думаю, использование "box" оправдано, для понимания размер чего будет тут указан
    private String boxColour; //тут, думаю, использование "box" оправдано, для понимания цвет чего будет тут указа
    private boolean isWorkDone;
    Scanner scanner;

    public BoxHandler() {
        isWorkDone = false;
        scanner = new Scanner(System.in);
        System.out.println(Messages.onHandlesCreationMessage);
        box = boxFactory();
    }

    /**
     * Метод предназначен для создания коробок в соответствии с введенными пользователем значениями size, colour
     * @apiNote значения size, colour задаются пользователем через консоль с проверкой, чтобы size был > 0, colour был не null и содержал текст
     * @return Box - экземпляр класса Box
     */
    public Box boxFactory(){
            while(boxSize == 0){
                System.out.printf(Messages.boxRequest, Messages.sizeRequirements);
                int input = scanner.nextInt();
                if(input <= 0){
                    System.out.printf(Messages.incorrectValueWarn, Messages.sizeRequirements);
                    continue;
                }
                boxSize = input;
                System.out.printf(Messages.setSize, boxSize);
                scanner.nextLine(); // т.к. при использовании nextLine() после next() возникает проблема с захватом nextLine() пред.перевода строки
            }
            while(boxColour == null || boxColour.isBlank()){
                System.out.printf(Messages.boxRequest, Messages.colourRequirements);
                String input = scanner.nextLine();
                if(input == null || input.isBlank()){
                    System.out.printf(Messages.incorrectValueWarn, Messages.colourRequirements);
                    continue;
                }
                boxColour = input;
                System.out.printf(Messages.setColour, boxColour);
            }
        return new Box(boxSize, boxColour);
    }

    /**
     * Метод выводит в консоль список команд для работы с коробкой
     */
    public void helpCommand() {
        System.out.println(Messages.listOfCommands);
    }

    /**
     * Метод читает введенный пользователем текст и обрабатывает команды
     */
    public void start() {
        System.out.println(Messages.listOfCommands);
        while (!isWorkDone) {
            System.out.println(Messages.enterCommand);
            String command = scanner.next().toLowerCase();
            String commandForSwitch = command.contains("_") ? command.substring(0, command.indexOf('_'))
                    : command;

            switch (commandForSwitch) {
                case "open" -> box.open();
                case "close" -> box.close();
                case "info" -> box.getInformation();
                case "take" -> box.removeItem();
                case "stop" -> stopWorking();
                case "help" -> helpCommand();
                case "colour" -> {
                    String colour = command.substring(command.indexOf('_') + 1);
                    box.setColour(colour);
                }
                case "put" -> {
                    String item = command.substring(command.indexOf('_') + 1);
                    box.placeItem(item);
                }
                default -> System.out.println(Messages.unknownCommand);
            }
        }
    }

    /**
     * Метод завершает работу scanner и работу программы
     */
    public void stopWorking() {
        isWorkDone = true;
        scanner.close();
        System.out.println(Messages.stopWorking);
    }
}
