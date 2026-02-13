package ru.otus.java.basic.homeworx.homework_lesson_19.io;

import ru.otus.java.basic.homeworx.homework_lesson_19.EmptyDirectoryException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для взаимодействия с пользователем
 * directory - директория, в которой находятся файлы, с которыми будет осуществляться работа
 * file - файл, с которым будет осуществляться работа
 * filesService - экземпляр класса FileService для работы с файлами
 * scanner - сканнер для работы с консолью
 * PATH - константа, задающая путь до директории ветки данного проекта
 * type - константа, задающая тип файла, с которым будет осуществляться работа
 * isWorkDone - флаг, показывающий завершена работа с программой (true) или нет (false)
 */
public class UserService {
    private File directory;
    private File file;
    private FilesService filesService;
    private Scanner scanner;
    private final String PATH = "src/main/java/ru/otus/java/basic/homeworx/homework_lesson_19/";
    private final String TYPE = ".txt";
    private boolean isWorkDone;


    public UserService() {
        isWorkDone = false;
        scanner = new Scanner(System.in);
        init();
    }

    /**
     * Метод на инициализации директории и экземпляра класса FileService.
     * Вызывается при создании объекта
     */
    public void init() {
        setDirectory();
        setFilesService();
    }

    /**
     * Метод установки директории
     * Формирует сообщение в консоль, читает введенную пользователем информацию и вызывает
     * метод проверки. Работает пока переменная directory не перестанет ссылаться на null
     */
    public void setDirectory() {
        while (directory == null) {
            System.out.print("Введите название папки для сканирования: ");
            String dirName = scanner.nextLine().trim();
            checkDirectory(dirName);
        }
    }

    /**
     * Метод для проверки указанной пользователем директории
     *
     * @param dirName - имя директории, введенное пользователем в консоль
     * @apiNote реализована проверка на null, на то, что файл является директорией, в отношении
     * директории у пользователя имеются права чтения
     */
    public void checkDirectory(String dirName) {
        if (dirName.isEmpty()) {
            System.out.println("Название папки не может быть пустым");
            return;
        }
        File tempDir = new File(PATH.concat(dirName));
        if (!tempDir.isDirectory()) {
            System.out.printf("Папка с именем %s не существует\n", dirName);
            return;
        }
        if (!tempDir.canRead()) {
            System.out.println("Нет доступа для чтения папки");
            return;
        }
        directory = tempDir;
    }

    /**
     * Метод для создания экземпляра класса FilesService.
     * Если в выбранной директории отсутствуют файлы заданного типа,
     * возникает исключение, при обработке которого метод установки директории запускается снова
     * Функционал смены директории не предусмотрен
     */
    public void setFilesService() {
        try {
            filesService = new FilesService(directory);
            filesService.printNames(TYPE);
        } catch (EmptyDirectoryException e) {
            System.out.println(e.getMessage());
            directory = null;
            init();
        }
    }

    /**
     * Метод для выбора пользователем файла
     *
     * @param fileName - введенное пользователем в консоль имя файла
     * @apiNote осуществляются проверки, что тип введенного файла должен соответствовать константному,
     * проверка на null
     */
    public void setFile(String fileName) {
        file = null;
        if (!fileName.endsWith(TYPE)) {
            System.out.println("Неверное наименование и/или тип файла");
        }
        file = filesService.find(fileName, TYPE);
        if (file != null) {
            System.out.println("Файл " + fileName + " выбран");
        } else {
            System.out.println("Файл " + fileName + " не найден");
        }
    }

    /**
     * Основной метод обработки команд. Читает введенный пользователем текст и обрабатывает команды
     */
    public void workWithFiles() {
        String listOfCommands = """
                
                Чтобы работать с файлами, используйте следующие команды:
                * commands - получить список команд;
                * choose_<имя_файла> - чтобы выбрать файл, с которым будете работать, например, choose_1.txt;
                * read - чтобы прочитать содержимое выбранного файла;
                * write - чтобы записать текст в выбранный файл;
                * exit - чтобы завершить работу программы.
                """;
        System.out.println(listOfCommands);

        while (!isWorkDone) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine().toLowerCase().trim();
            String commandForSwitch = command.contains("_") ?
                    command.substring(0, command.indexOf("_") + 1) : command;

            switch (commandForSwitch) {
                case "commands" -> System.out.println(listOfCommands);
                case "choose_" -> setFile(command.substring(command.indexOf("_") + 1));
                case "read" -> {                         // реализована проверка прав чтения
                    if (file == null) {
                        System.out.println("Сначала нужно выбрать файл");
                        continue;
                    }
                    if (!file.canRead()) {
                        System.out.println("Нет доступа для чтения файла");
                        continue;
                    }
                    try {
                        filesService.read(file);
                    } catch (IOException e) {
                        System.out.println("При работе с файлом возникла проблема. Выберите другой файл");
                        file = null;
                    }
                }
                case "write" -> {                        // реализована проверка прав редактирования
                    if (file == null) {
                        System.out.println("Сначала нужно выбрать файл");
                        continue;
                    }
                    if (!file.canWrite()) {
                        System.out.println("Нет доступа для записи в файл");
                        continue;
                    }
                    System.out.println("Наберите текст, который необходимо добавить в файл " + file.getName() + ": ");
                    String input = scanner.nextLine();
                    String text = "\n" + input;
                    try {
                        filesService.write(file, text);
                    } catch (IOException e) {
                        System.out.println("При работе с файлом возникла проблема. Выберите другой файл");
                        file = null;
                    }
                }
                case "exit" -> stopWorking();

                default -> System.out.println("Неизвестная команда");
            }

        }
    }

    /**
     * Метод завершает работу scanner и работу программы
     */
    public void stopWorking() {
        isWorkDone = true;
        scanner.close();
        System.out.println("Работа завершена");
    }
}

