package ru.otus.java.basic.homeworx.homework_lesson_32;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Обработчик одного клиентского соединения (Runnable).
 * Читает запрос, парсит его, передаёт диспетчеру и закрывает ресурсы.
 */
public class RequestHandler implements Runnable {
    private final Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private RequestParser parser;
    private final Dispatcher dispatcher;
    private final HttpServer httpServer;

    private static final Logger logger = LogManager.getLogger(RequestHandler.class);

    public RequestHandler(Socket socket, HttpServer httpServer) {
        this.socket = socket;
        this.httpServer = httpServer;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            parser = new RequestParser(reader);
        } catch (IOException e) {
            logger.error("Exception while opening input stream {}", e.getMessage());
        }
        dispatcher = new Dispatcher();
    }

    @Override
    public void run() {
        try {
            parser.parseInput();
            dispatcher.handle(parser, writer, httpServer);
        } catch (IOException e) {
            logger.error("Error while processing request {}", e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                logger.error("Error while closing resources {}", e.getMessage());
            }
        }

    }


}
