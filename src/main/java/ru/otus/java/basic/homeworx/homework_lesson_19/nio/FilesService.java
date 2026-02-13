package ru.otus.java.basic.homeworx.homework_lesson_19.nio;

import ru.otus.java.basic.homeworx.homework_lesson_19.EmptyDirectoryException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с файлами
 * path - путь директории, в которой находятся файлы, с которыми будет осуществляться работа
 */
public class FilesService {
    Path dirPath;

    /**
     * Путь к директории задается при создании сервиса
     *
     * @param dirPath - заданная пользователем директория
     */
    public FilesService(Path dirPath) {
        this.dirPath = dirPath;
    }

    /**
     * Метод для поиска в выбранной директории файлов заданного типа
     *
     * @param type - тип файлов для поиска
     * @return список объектов типа Path, соответствующих заданному типу.
     * @apiNote Проверка, что объект является файлом дополнительно не осуществляется, т.к. наполнение списка
     * осуществляется из метода visitFile. Обход директории осуществлен посредством walkFileTree
     */
    public List<Path> getFilesOfCertainType(String type) {
        List<Path> listOfFiles = new ArrayList<>();
        try {
            Files.walkFileTree(dirPath, new MyFileVisitor(listOfFiles, type));
        } catch (IOException e) {
            System.out.println("Возникла ошибка при получении доступа к файлам," +
                    "содержащимся в папке " + dirPath.getFileName());
        }
        return listOfFiles;
    }

    /**
     * Метод для поиска в выбранной директории файла, имя которого совпадает
     * с заданным пользователем именем файла, включая тип файла
     *
     * @param fileName - заданное пользователем имя файла
     * @param type     - тип файла
     * @return найденный объект Path или null, если объект не найден
     * @apiNote передаваемое в метод имя файла не может быть null, проверка не нужна
     */
    public Path find(String fileName, String type) {
        List<Path> listOfFiles = getFilesOfCertainType(type);
        return listOfFiles.stream().filter(file ->
                file.endsWith(fileName)).findFirst().orElse(null);
    }

    /**
     * Метод для вывода в консоль списка файлов выбранного типа, находящихся в выбранной директории
     *
     * @param type - тип файла
     * @throws EmptyDirectoryException в случае, если в директории отсутствуют файлы заданного типа
     */
    public void printNames(String type) throws EmptyDirectoryException {
        List<Path> filesList = getFilesOfCertainType(type);

        if (filesList.isEmpty()) {
            throw new EmptyDirectoryException(dirPath, type);
        }
        System.out.printf("\nСписок файлов (%s), содержащихся в папке: %s\n",
                type, dirPath.getFileName());
        filesList.forEach(file ->
                System.out.println(file.getFileName()));
    }

    /**
     * Методы для вывода в консоль содержимого выбранного файла
     *
     * @param filePath выбранный пользователем путь к файлу
     * @throws IOException
     * @apiNote проверка файла на null, на возможность чтения осуществляется в FilesHandler
     * Чтение и вывод осуществляются по-байтово, для экономии памяти размер массива, в который из буфера записываются
     * значения установлен меньше размера буфера
     */
    public void read(Path filePath) throws IOException {
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            byte[] bytes = new byte[1024];
            while (channel.read(buffer) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    int length = Math.min(bytes.length, buffer.remaining());
                    buffer.get(bytes, 0, length);
                    System.out.println(new String(bytes, 0, length, StandardCharsets.UTF_8));
                }
                buffer.clear();
            }
            System.out.println();
        }
    }

    /**
     * Метод для записи в выбранный файл введенного пользователем в консоль текста
     *
     * @param filePath - выбранный пользователем путь к файлу
     * @param text     - текст, введенный пользователем в консоль
     * @throws IOException
     * @apiNote запись осуществляется в конец файла, для этого используется StandardOpenOption.APPEND,
     * в рамках метода wrap создается массив байт, равный сообщению, размер буфера равен размеру массива
     */
    public void write(Path filePath, String text) throws IOException {
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.APPEND)) {
            ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
            channel.write(buffer);

        }
    }

    /**
     * Класс для задания правил обхода директории
     * type - заданный тип файла
     * listOfFiles - список файлов директории
     *
     * @apiNote поскольку нет необходимости в переопределении всех методов FileVisitor, установлено расширение
     * класса SimpleFileVisitor
     */
    class MyFileVisitor extends SimpleFileVisitor<Path> {

        String type;
        List<Path> listOfFiles;

        public MyFileVisitor(List<Path> listOfFiles, String type) {
            this.listOfFiles = listOfFiles;
            this.type = type;
        }

        /**
         * Метод, задающий правило до начала обхода директории
         *
         * @param dir   a reference to the directory
         * @param attrs the directory's basic attributes
         * @return - обойти все файлы в заданной пользователем директории, пропускать поддиректории
         * @throws IOException
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException {
            if (dir.getParent().equals(dirPath)) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * Метод, задающий правило обхода файлов
         *
         * @param file  a reference to the file
         * @param attrs the file's basic attributes
         * @return - обходятся все файлы заданной директории
         * @throws IOException
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
            if (file.getFileName().toString().endsWith(type)) {
                listOfFiles.add(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
