package ru.otus.java.basic.homeworx.homework_lesson_20.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс клиента с использованием пакета io
 * PORT - константа порта, который прослушивает сервер
 */
public class Client {
    private final int PORT = 8080;

    /**
     * Метод для запуска клиента.
     *
     * @apiNote создается Сокет с хостом на локальной машине, прослушиваемым сервером портом
     * Для передачи используется сервис PrintWriter, в отношении которого возможно установить autoFlush
     * Для чтения от Сервера используется сервис BufferedReader с функционалом чтения строк и буфером для
     * взаимодействия с потоком. Для чтения из консоли - Scanner.
     * Все 4 сервиса закрываются сами (try-with-resources).
     * <p>
     * При работе читается введенный в консоль пример, который через writer записывается в OutputStream для чтения
     * сервером. Получаемый ответ сервера читается из InputStream через reader и выводится консоль.
     * Бесконечный цикл завершается вводом в консоль ключевого слова "exit"
     *
     */
    public void start() {
        try (Socket socket = new Socket("localhost", PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             Scanner consoleReader = new Scanner(System.in);
        ) {
            printMessage(reader);
            while (true) {
                String consoleMessage = consoleReader.nextLine();
                writer.println(consoleMessage);
                if (consoleMessage.equals("exit")) {
                    System.out.println("До свидания!");
                    break;
                }
                System.out.println("Ответ: " + reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для чтения из InputStream многострокового пакета
     *
     * @param reader - читает из InputStream пакеты от сервера
     * @throws IOException
     * @apiNote в качестве указателя конца пакета использован пустой пакет, отправляемый сразу после читаемого
     */
    private void printMessage(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                reader.readLine();
                break;
            }
            System.out.println(line);
        }
    }

    /**
     * запуск клиента
     *
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
