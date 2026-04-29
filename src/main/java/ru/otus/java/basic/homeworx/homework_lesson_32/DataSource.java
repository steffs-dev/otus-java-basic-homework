package ru.otus.java.basic.homeworx.homework_lesson_32;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Источник данных – управляет подключением к PostgreSQL и созданием таблицы items.
 */
public class DataSource {
    private final String URL = "jdbc:postgresql://localhost:5432/http_client_1";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "admin";
    Connection connection;

    private static final Logger log = LogManager.getLogger(DataSource.class);

    /**
     * Устанавливает соединение с БД и создает таблицу items, если она не существует.
     */
    public DataSource() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        try {
            createTable();
        } catch (SQLException e) {
            log.error("Failed to create items table in DB. {}",  e.getMessage());
            throw new RuntimeException("Failed to create items table in DB " + e);
        }
    }

    /**
     * Создает таблицу items с полями: id (BIGSERIAL, PK), name, price.
     * Добавляет ограничение: name не пустое, price > 0.
     */
    private void createTable() throws SQLException {
        String sql = "" +
                "CREATE TABLE IF NOT EXISTS items(" +
                "id BIGSERIAL PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "price INT NOT NULL," +
                "CONSTRAINT not_empty_data CHECK(name != '' AND price > 0));";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            statement.close();
            log.info("Table reg_users created in DB");
        }
    }

    /** Возвращает активное соединение с БД. */
    public Connection getConnection() {
        return connection;
    }
}
