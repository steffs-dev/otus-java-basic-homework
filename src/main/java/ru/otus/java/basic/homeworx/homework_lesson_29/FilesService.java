package ru.otus.java.basic.homeworx.homework_lesson_29;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Сервис для работы с файловой системой.
 * Предоставляет методы для работы с директорией и файлами
 * directory - директория, в которой находятся файлы
 */
public class FilesService {
    private final File directory;

    /**
     * Конструктор
     *
     * @param directory объект File, представляющий рабочую директорию
     */
    public FilesService(File directory) {
        this.directory = directory;
    }

    /**
     * Возвращает массив файлов (с проверкой, что это файлы) из рабочей директории.
     *
     * @return массив файлов или null, если директория недоступна
     */
    private File[] allFilesOfDirectory() {
        return directory.listFiles(File::isFile);
    }

    /**
     * Возвращает файл по его имени.
     *
     * @param fileName имя искомого файла
     * @return объект File, если файл найден, иначе null
     */
    public File getFile(String fileName) {
        return Arrays.stream(allFilesOfDirectory())
                .filter(f -> f.getName().equals(fileName))
                .findFirst().orElse(null);
    }

    /**
     * Формирует строку со списком всех файлов в директории.
     *
     * @return строка, содержащая имена файлов, разделенные запятой и пробелом
     */
    public String listOfFiles() {
        StringBuilder sb = new StringBuilder("Файлы директории: ");
        for (File f : allFilesOfDirectory()) {
            sb.append(f.getName()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    /**
     * Выводит содержимое файла в консоль построчно.
     *
     * @param file файл для чтения
     * @throws IOException если возникают ошибки ввода-вывода
     */
    public void read(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Подсчитывает количество вхождений указанной строки в файле.
     * Поиск производится с учетом возможных пересечений через границы строк:
     * для этого используется StringBuilder, в начало которого добавляется конец предыдущей строки
     * (количество символов равно размеру искомого текста), чтобы не пропустить вхождения,
     * начинающиеся в конце одной строки и продолжающиеся в начале следующей
     *
     * @param file       файл для поиска
     * @param textToFind искомая строка
     * @throws IOException если возникают ошибки ввода-вывода
     */
    public void countMatches(File file, String textToFind) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            if (textToFind == null || textToFind.isEmpty()) {
                System.out.println("Неверный формат текста для поиска");
                return;
            }
            String line;
            StringBuilder widerLine = new StringBuilder();
            int count = 0;
            while ((line = reader.readLine()) != null) {
                widerLine.append(line);
                int index = 0;
                while ((index = widerLine.indexOf(textToFind, index)) != -1) {
                    count++;
                    index += textToFind.length();
                }
                widerLine.setLength(0);
                widerLine.append(line.substring(line.length() - textToFind.length()));
            }
            System.out.println(textToFind + " встречается в тексте " + count + " раз");
        }
    }
}
