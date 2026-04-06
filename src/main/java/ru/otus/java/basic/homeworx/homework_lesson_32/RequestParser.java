package ru.otus.java.basic.homeworx.homework_lesson_32;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Парсер HTTP-запроса.
 * Извлекает метод, путь, тело запроса и формирует routingKey
 * (например "POST /items").
 */
public class RequestParser {
    private final BufferedReader reader;
    private HttpMethods method;
    private String uri;
    private String body;
    private String routingKey;

    private static final Logger log = LogManager.getLogger(RequestParser.class);

    public RequestParser(BufferedReader reader) {
        this.reader = reader;
    }

    public HttpMethods getMethod() {
        return method;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getUri() {
        return uri;
    }

    public String getBody() {
        return body;
    }

    /**
     * Основной метод парсинга:
     * 1) Читает стартовую строку (GET /items HTTP/1.1)
     * 2) Определяет метод (GET) и путь (/items)
     * 3) Для POST/PUT читает заголовки до пустой строки, извлекает Content-Length
     * 4) Читает тело запроса указанной длины
     */
    public void parseInput() throws IOException {
        String startLine = reader.readLine();
        if (startLine == null) {
            return;
        }
        parseMethod(startLine);
        parseUri(startLine);
        routingKey = method + " " + uri;
        if (method.equals(HttpMethods.POST) || method.equals(HttpMethods.PUT)) {
            String line;
            int contentLength = 0;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                if (line.startsWith("Content-Length:")) {
                    contentLength = parseHeader(line);
                }
            }
            parseBody(contentLength);
        }
    }

    /**
     * Извлекает числовое значение из заголовка "Content-Length: xxx".
     */
    private int parseHeader(String line) {
        int beginIndex = line.indexOf(':') + 1;
        return Integer.parseInt(line.substring(beginIndex).trim());
    }

    /**
     * Определяет HTTP-метод по началу стартовой строки.
     */
    private void parseMethod(String line) {
        for (HttpMethods m : HttpMethods.values()) {
            if (line.startsWith(m.toString())) {
                method = m;
                return;
            }
        }
        throw new IllegalArgumentException("Invalid HTTP method: " + line);
    }

    /**
     * Извлекает URI из стартовой строки.
     */
    private void parseUri(String line) {
        if (method == null) {
            throw new IllegalArgumentException("Method not parsed: " + line);
        }
        int startIndex = method.toString().length() + 1;
        int endIndex = line.indexOf(" ", startIndex);
        uri = line.substring(startIndex, endIndex);
    }

    /**
     * Читает тело запроса (ровно contentLength символов).
     */
    private void parseBody(int contentLength) throws IOException {
        if (contentLength > 0) {
            char[] buffer = new char[contentLength];
            int read = reader.read(buffer, 0, contentLength);
            if (read == contentLength) {
                body = new String(buffer);
            } else {
                log.warn("Content-length {}, read {} ", contentLength, read);
            }
        }
    }
}
