package ru.otus.java.basic.homeworx.homework_lesson_32;

import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс DAO для доступа к данным
 *
 * @param <T> тип сущности, с которой работает DAO
 */
public interface DAO<T> {

    /**
     * Возвращает список всех записей.
     */
    List<T> getAll() throws SQLException;

    /**
     * Сохраняет объект в БД (при вставке генерируется ID).
     */
    T save(T t) throws SQLException;

    /**
     * Закрывает соединение с БД.
     */
    void closeConnection() throws SQLException;
}
