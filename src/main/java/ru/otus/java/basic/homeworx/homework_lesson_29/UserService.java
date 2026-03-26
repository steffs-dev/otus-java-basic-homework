package ru.otus.java.basic.homeworx.homework_lesson_29;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Сервис пользовательского интерфейса.
 * Отвечает за взаимодействие с пользователем через консоль, обработку команд
 * и управление выбором файла для последующих операций.
 * scanner - сканер для чтения команд пользователя
 * filesService - сервис для работы с файловой системой
 * DIR - константный путь к директории с файлами
 * file - текущий выбранный файл
 * isRunning - флаг работы цикла обработки команд
 */
public class UserService {
    private final Scanner scanner;
    private final FilesService filesService;
    private final String DIR = "src/main/java/ru/otus/java/basic/homeworx/homework_lesson_29/files";
    private File file;
    private boolean isRunning;

    /**
     * Конструктор
     * Инициализирует сканер, сервис файлов и устанавливает флаг работы
     */
    public UserService() {
        scanner = new Scanner(System.in);
        filesService = new FilesService(new File(DIR));
        isRunning = true;
    }

    /**
     * Запускает главный цикл обработки команд.
     * Выводит список доступных команд и ждет ввода от пользователя.
     * В зависимости от команды вызывает соответствующие методы.
     */
    public void start() {
        String listOfCommands = """
                
                Чтобы работать с файлами, используйте следующие команды:
                * commands - получить список команд;
                * files - получить список файлов;
                * choose_<имя_файла> - чтобы выбрать файл, с которым будете работать, например, choose_1.txt;
                * read - чтобы прочитать содержимое выбранного файла;
                * count_<текст> - найти количество совпадений в тексте файла с введенным символом(ами), например, count_123;
                * exit - чтобы завершить работу программы.
                """;
        System.out.println(listOfCommands);
        while (isRunning) {
            System.out.print("Выберите команду: ");
            String command = scanner.nextLine().toLowerCase().trim();
            String commandForSwitch = (command.contains("_")) ? command.substring(0, command.indexOf("_") + 1)
                    : command;

            switch (commandForSwitch) {
                case "commands" -> {
                    System.out.println(listOfCommands);
                }
                case "files" -> {
                    System.out.println(filesService.listOfFiles());
                }
                case "choose_" -> {
                    String fileName = command.substring(command.indexOf("_") + 1);
                    chooseFile(fileName);
                }
                case "read" -> {
                    if (checkFile()) {
                        try {
                            filesService.read(file);
                        } catch (IOException e) {
                            System.out.println("Ошибка при чтении из файла. Выберите другой файл");
                            e.printStackTrace();
                            file = null;
                        }
                    }
                }
                case "count_" -> {
                    if (checkFile()) {
                        String textToFind = command.substring(command.indexOf("_") + 1);
                        try {
                            filesService.countMatches(file, textToFind);
                        } catch (IOException e) {
                            System.out.println("Ошибка при работе с файлом");
                            e.printStackTrace();
                            file = null;
                        }
                    }
                }
                case "exit" -> {
                    stop();
                }
                default -> {
                    System.out.println("Неизвестная команда");
                }
            }
        }
    }

    /**
     * Выбирает файл по имени в директории
     *
     * @param fileName имя файла для выбора
     */
    private void chooseFile(String fileName) {
        file = null;
        file = filesService.getFile(fileName);
        if (file == null) {
            System.out.println("Файл с именем " + fileName + " не найден");
        } else {
            System.out.println("Выбран файл " + fileName);
        }
    }

    /**
     * Проверяет выбран ли файл и доступен ли он для чтения
     *
     * @return true если файл выбран и доступен для чтения, иначе false
     */
    private boolean checkFile() {
        if (file == null) {
            System.out.println("Файл не выбран");
            return false;
        }
        if (!file.canRead()) {
            System.out.println("Нет доступа для чтения файла");
            return false;
        }
        return true;
    }

    /**
     * Останавливает главный цикл, завершая работу программы
     */
    public void stop() {
        isRunning = false;
    }

}
