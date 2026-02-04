package ru.otus.java.basic.homeworx.homework_lesson_17.solution_with_map;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс телефонная книга. Приложение создано с использованием структуры данных Map (альтернатива Set)
 * Map<String, String> records - коллекция для хранения номеров телефонов и ФИО.
 * Инициализируется при создании класса.
 *
 * @apiNote тип данных номера - String, чтобы в дальнейшем иметь возможность изменения формата
 * номера за счет добавления "+", "-". ФИО хранятся в одной строке
 *
 */
public class PhoneBook {
    private final Map<String, String> records;

    public PhoneBook() {
        records = new HashMap<>();
    }

    public Map<String, String> getRecords() {
        return records;
    }

    /**
     * Метод для добавления записи в телефонную книгу
     *
     * @param number   - номер телефона. Не может быть null, пустым, не соответствовать шаблону
     * @param fullName - ФИО. Не может быть null, пустым.
     */
    public void add(String number, String fullName) {
        if (isTextInappropriate(number) || isTextInappropriate(fullName)) {
            System.out.println("Номер телефона и имя должны не быть null или пустыми");
            return;
        }
        if (isNumberInappropriate(number)) {
            System.out.println("""
                    Неверный формат телефонного номера.
                    Номер должен быть длиной 11 цифр и начинаться с 7 или 8
                    """);
            return;
        }
        String previousAddition = records.putIfAbsent(number, fullName.trim()); //если запись внесена, возвращается null, иначе value записи, имеющей тот же ключ

        if (previousAddition == null) {
            System.out.println("Номер телефона: " + number +
                    " успешно внесен в телефонную книгу");
        } else {
            System.out.println("Номер телефона: " + number +
                    " ранее уже был внесен в телефонную книгу " +
                    "для контакта: " + previousAddition);
        }
    }

    /**
     * Метод для поиска всех номеров телефона, ассоциированных с заданной фамилией
     *
     * @param surname заданная фамилия, по которой осуществляется поиск
     * @apiNote в поиске участвует только фамилия без имени и отчества
     */
    public void find(String surname) {
        if (isTextInappropriate(surname)) {
            System.out.println("Не могу найти - фамилия должна не быть null или пустой");
            return;
        }

        String inputSurname = extractSurname(surname);

        List<String> listOfNumbers = new ArrayList<>();

        listOfNumbers = records.entrySet().stream()
                .filter(el ->
                        extractSurname(el.getValue())
                                .equals(inputSurname)
                )
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        printFoundNumbers(listOfNumbers, inputSurname);
    }

    /**
     * Метод для поиска телефонного номера в книге. Выводит сообщение о результате
     *
     * @param number - заданный номер для поиска. Номер должен не быть null или пустым, должен соответствовать заданному формату
     */
    public void containsPhoneNumber(String number) {
        if (isTextInappropriate(number) || isNumberInappropriate(number)) {
            System.out.println("""
                    Неверный формат телефонного номера.
                    Номер должен быть длиной 11 цифр и начинаться с 7 или 8.
                    Номер телефона должен не быть null или пустым");
                    """);
            return;
        }
        System.out.printf("Телефонная книга%s содержит телефонный номер: %s\n",
                (records.containsKey(number) ? "" : " не"),
                number);
    }

    /**
     * Метод для проверки соответствия номера заданному формату
     *
     * @param number - проверяемый номер
     * @return true (если номер не соответствует формату), false (если соответствует)
     * @apiNote заданный формат: всего цифр: 11, первая должна быть 7 и 8,
     * остальные 10 цифр - от 0 до 9. Должен вызываться после проверки на null
     */
    public boolean isNumberInappropriate(String number) {
        String REGEX = "^[7-8]\\d{10}$";
        return !number.matches(REGEX);
    }

    /**
     * Метод проверки текста на null и пустоту, в т.ч пробелы и табы
     *
     * @param text - проверяемый текст
     * @return true (если текст null или blank), false (если нет)
     */
    public boolean isTextInappropriate(String text) {
        return text == null || text.isBlank();
    }

    /**
     * Метод для извлечения фамилии из строки
     *
     * @param text - входящее значение
     * @return извлеченное значение фамилии
     * @apiNote берется набот символов до первого пробела. Должен вызываться после проверки на null
     */
    public String extractSurname(String text) {
        String trimmedText = text.trim();
        int index = trimmedText.indexOf(" ");
        return index == -1 ? trimmedText : trimmedText.substring(0, index);
    }

    /**
     * Метод для вывода в консоль списка найденных номеров
     *
     * @param listOfNumbers - список найденных номеров
     * @param surname       - фамилия, по которой осуществлялся поиск номеров
     */
    public void printFoundNumbers(List<String> listOfNumbers, String surname) {
        if (!listOfNumbers.isEmpty()) {
            System.out.println(surname + " имеет следующие телефонные номера:\n"
                    + listOfNumbers);
        } else {
            System.out.println(surname + " не имеет телефонных номеров");
        }
    }

    /**
     * Метод для вывода в консоль всех записей телефонной книги
     */
    public void printRecordsMap() {
        System.out.println("-----------------------\n" +
                "Телефонная книга:");
        records.entrySet().stream()
                .forEach(el ->
                        System.out.println("* Номер: " + el.getKey() +
                                " ФИО: " + el.getValue()));
        System.out.println("-----------------------");
    }
}
