package ru.otus.java.basic.homeworx.homework_lesson_19.io;

import ru.otus.java.basic.homeworx.homework_lesson_19.EmptyDirectoryException;

import java.io.*;
import java.util.Arrays;

/**
 * Класс для работы с файлами
 * directory - директория, в которой находятся файлы, с которыми будет осуществляться работа
 */
public class FilesService {

    private File directory;

    /**
     * Директория задается при создании сервиса
     *
     * @param directory - заданная пользователем директория
     */
    public FilesService(File directory) {
        this.directory = directory;
    }

    /**
     * Метод для поиска в выбранной директории файлов заданного типа
     *
     * @param type - тип файлов для поиска
     * @return массив объектов типа File, соответствующих заданному типу с проверкой,
     * что найденный объект является файлом
     */
    public File[] getFilesOfSpecifiedType(String type) {
        return directory.listFiles(f ->
                f.getName().endsWith(type) && f.isFile());
    }

    /**
     * Метод для поиска в выбранной директории файла, имя которого совпадает
     * с заданным пользователем именем файла, включая тип файла
     *
     * @param fileName - заданное пользователем имя файла
     * @param type     - тип файла
     * @return найденный объект File или null, если объект не найден
     * @apiNote передаваемое в метод имя файла не может быть null, проверка не нужна
     */
    public File find(String fileName, String type) {
        return Arrays.stream(getFilesOfSpecifiedType(type))
                .filter(f -> f.getName().equals(fileName))
                .findFirst().orElse(null);
    }

    /**
     * Метод для вывода в консоль списка файлов выбранного типа, находящихся в выбранной директории
     *
     * @param type - тип файла
     * @throws EmptyDirectoryException в случае, если в директории отсутствуют файлы заданного типа
     */
    public void printNames(String type) throws EmptyDirectoryException {
        File[] array = getFilesOfSpecifiedType(type);
        if (array.length == 0) {
            throw new EmptyDirectoryException(directory.getName(), type);
        }
        System.out.printf("\nСписок файлов (%s), содержащихся в папке: %s\n", type, directory.getName());
        Arrays.stream(array).forEach(file ->
                System.out.println(file.getName()));
    }

    /**
     * Методы для вывода в консоль содержимого выбранного файла
     *
     * @param file выбранный пользователем файл
     * @throws IOException
     * @apiNote проверка файла на null, на возможность чтения осуществляется в FilesHandler
     * Чтение и вывод осуществляются по строкам
     */
    public void read(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Метод для записи в выбранный файл введенного пользователем в консоль текста
     *
     * @param file - выбранный пользователем файл
     * @param text - текст, введенный пользователем в консоль
     * @throws IOException
     * @apiNote запись осуществляется в конец файла, для этого используется RandomAccessFile,
     * устанавливающий курсор после последнего символа
     */
    public void write(File file, String text) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(file, "rw")) {
            accessFile.seek(file.length());
            accessFile.writeBytes(text);
        }
    }
}
