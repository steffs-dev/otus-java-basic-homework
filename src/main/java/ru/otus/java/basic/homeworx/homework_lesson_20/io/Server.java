package ru.otus.java.basic.homeworx.homework_lesson_20.io;

import ru.otus.java.basic.homeworx.homework_lesson_20.common.IllegalTaskFormatException;
import ru.otus.java.basic.homeworx.homework_lesson_20.common.MathHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс клиента с использованием пакета io
 * PORT - константа порта, который прослушивает сервер
 *
 * @apiNote проверка соответствия полученного примера шаблону и математическая логика вынесены в класс MathHandler
 */
public class Server {
    private final int PORT = 8080;

    /**
     * Метод для запуска сервера.
     *
     * @apiNote создается Сокет сервера с прослушиваемым сервером портом.
     * Сервер после создания находится в ожидании подключения клиента. При подключении клиента создается Сокет клиента
     * Для передачи используется сервис PrintWriter, в отношении которого возможно установить autoFlush
     * Для чтения от Сервера используется сервис BufferedReader с функционалом чтения строк и буфером для
     * взаимодействия с потоком.
     * Все 4 сервиса закрываются сами (try-with-resources).
     * <p>
     * При подключении клиента, ему отправляется приветственное сообщение, затем сервер переходит в режим ожидания.
     * При получении пакета он передается для обработки в класс MathHandler, результат обработки направляется клиенту.
     * При несоответствии формата примера шаблону, выбрасывается IllegalTaskFormatException, при обработке которого
     * клиенту направляется сообщение.
     * Бесконечный цикл завершается при получении от клиента ключевого слова "exit"
     *
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            ) {
                System.out.println("Клиент подключен, адрес " + clientSocket.getInetAddress());
                greeting(writer);

                String inputMessage = reader.readLine().toLowerCase().trim();

                while (!inputMessage.equals("exit")) {
                    try {
                        writer.println("Ответ: " + MathHandler.generateResult(inputMessage));
                    } catch (IllegalTaskFormatException e) {
                        writer.println(e.getMessage());
                    }
                    inputMessage = reader.readLine().toLowerCase().trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Клиент отключился");
    }

    /**
     * Метод для отправки клиенту многострокового пакета
     *
     * @param writer - пишет пакеты в OutputStream
     * @apiNote в качестве указателя конца пакета использован пустой пакет, отправляемый сразу после читаемого
     */
    public void greeting(PrintWriter writer) {
        writer.println("""
                --------------------------------------------------------------------
                Приветствую!
                Эта программа - калькулятор, которая умеет складывать, вычетать,
                умножать и делить.
                Чтобы получить ответ введи числа и математическое действие:
                сложить (+), вычесть (-), умножить (*) или разделить (/) между ними,
                например: 3 * 5, и нажми enter.
                Чтобы завершить программу, напиши exit
                --------------------------------------------------------------------
                """);
        writer.println();
    }

    /**
     * запуск сервера
     *
     * @param args
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
