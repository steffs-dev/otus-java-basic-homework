package ru.otus.java.basic.homeworx.homework_lesson_32;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworx.homework_lesson_32.Entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Реализация DAO для сущности Item (таблица items).
 */
public class DAO_Impl implements DAO<Item> {
    private Connection connection;

    private static final Logger log = LogManager.getLogger(DAO_Impl.class);

    /**
     * Конструктор создает соединение через DataSource и инициализирует таблицу items.
     */
    public DAO_Impl() throws SQLException {
        DataSource dataSource = new DataSource();
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<Item> getAll() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT id, name, price FROM items;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(mapToItem(resultSet));
                }
            }
            return Collections.unmodifiableList(items);
        }
    }

    @Override
    public Item save(Item item) throws SQLException {
        String sql = "INSERT INTO items (name, price) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getLong(1));
                }
            } catch (SQLException e) {
                log.warn("Failed to get item ID. Item name {}", item.getName());
            }
            return item;
        }
    }

    /**
     * Преобразует текущую строку ResultSet в объект Item.
     */
    private Item mapToItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getLong("id"));
        item.setName(resultSet.getString("name"));
        item.setPrice(resultSet.getInt("price"));
        return item;
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
