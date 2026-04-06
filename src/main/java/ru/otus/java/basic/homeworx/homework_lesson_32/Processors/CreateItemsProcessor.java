package ru.otus.java.basic.homeworx.homework_lesson_32.Processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworx.homework_lesson_32.Entities.Item;
import ru.otus.java.basic.homeworx.homework_lesson_32.HttpServer;
import ru.otus.java.basic.homeworx.homework_lesson_32.RequestParser;

import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Обработчик для маршрута "POST /items".
 * Принимает JSON с данными нового товара, сохраняет в БД и возвращает
 * созданный объект с присвоенным ID.
 */
public class CreateItemsProcessor implements Processor {

    private static final Logger log = LogManager.getLogger(CreateItemsProcessor.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(RequestParser requestParser, PrintWriter writer, HttpServer httpServer) {
        System.out.println(requestParser.getBody());
        String response;
        try {
            Item receivedItem = mapper.readValue(requestParser.getBody(), new TypeReference<Item>() {
            });
            System.out.println(receivedItem);
            Item savedItem = httpServer.save(receivedItem);
            String jsonItem = mapper.writeValueAsString(savedItem);
            response = "" +
                    "HTTP/1.1 201 Created\r\n" +
                    "Content-Type: application/json\r\n" +
                    "\r\n" +
                    jsonItem;
            writer.println(response);
        } catch (JsonProcessingException e) {
            log.error("Error while parsing JSON to Item {}", e.getMessage());
            response = "" +
                    "HTTP/1.1 500 Internal Server Error\r\n";
            writer.println(response);
        } catch (SQLException e) {
            log.error("Error while saving Item to DB {}", e.getMessage());
            response = "" +
                    "HTTP/1.1 500 Internal Server Error\r\n";
            writer.println(response);
        }
    }
}
