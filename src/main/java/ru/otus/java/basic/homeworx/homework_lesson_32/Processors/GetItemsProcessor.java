package ru.otus.java.basic.homeworx.homework_lesson_32.Processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworx.homework_lesson_32.Entities.Item;
import ru.otus.java.basic.homeworx.homework_lesson_32.HttpServer;
import ru.otus.java.basic.homeworx.homework_lesson_32.RequestParser;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик для маршрута "GET /items".
 * Возвращает список всех товаров из БД в формате JSON.
 */
public class GetItemsProcessor implements Processor {

    private static final Logger log = LogManager.getLogger(GetItemsProcessor.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(RequestParser requestParser, PrintWriter writer, HttpServer httpServer) {
        String response;
        List<Item> items = new ArrayList<>();
        try {
            items = httpServer.getItems();
        } catch (SQLException e) {
            log.error("Failed to get items from http server {}", e.getMessage());
            response = "" +
                    "HTTP/1.1 500 Internal Server Error\r\n";
            writer.println(response);        }
        try {
            String jsonItem = mapper.writeValueAsString(items);
            response = "" +
                    "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n" +
                    "\r\n" +
                    jsonItem;
            writer.println(response);
        } catch (JsonProcessingException e) {
            log.error("Error in conversion of Item to JSON {}", e.getMessage());
            response = "" +
                    "HTTP/1.1 500 Internal Server Error\r\n";
            writer.println(response);
        }
    }
}
