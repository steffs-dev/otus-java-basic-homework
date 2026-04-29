package ru.otus.java.basic.homeworx.homework_lesson_32;

import ru.otus.java.basic.homeworx.homework_lesson_32.Entities.Item;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервисный слой для работы с БД (делегирует вызовы DAO).
 */
public class DBService {
    private DAO<Item> dao;

    public DBService() throws SQLException {
        this.dao = new DAO_Impl();
    }

    /**
     * Возвращает все товары из БД.
     */
    public List<Item> getAll() throws SQLException {
        return dao.getAll();
    }

    /**
     * Сохраняет товар в БД.
     */
    public Item save(Item item) throws SQLException {
        return dao.save(item);
    }

    /**
     * Закрывает соединение с БД.
     */
    public void closeConnection() throws SQLException {
        dao.closeConnection();
    }
}
