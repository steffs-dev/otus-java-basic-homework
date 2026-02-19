package ru.otus.java.basic.homeworx.homework_lesson_20.nio;

import ru.otus.java.basic.homeworx.homework_lesson_20.common.IllegalTaskFormatException;
import ru.otus.java.basic.homeworx.homework_lesson_20.common.MathHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Класс клиента с использованием пакета nio
 * PORT - константа порта, который прослушивает сервер. By default - значения из интерфейса NioCommunicator. Может быть задан при создании экземпляра класса
 * HEADER_SIZE - константа размера буфера заголовков. By default - значения из интерфейса NioCommunicator. Может быть задан при создании экземпляра класса
 * BUFFER_SIZE - константа размера буфера сообщений. By default - значения из интерфейса NioCommunicator. Может быть задан при создании экземпляра класса
 * Класс имплементиентирует интерфейс NioCommunicator, содержащий все методы для работы с пакетами (общие как для клиента, так и для сервера)
 */
public class Server implements NioCommunicator {
    private int PORT = NioCommunicator.PORT;
    private int HEADER_SIZE = NioCommunicator.HEADER_SIZE;
    private int BUFFER_SIZE = NioCommunicator.BUFFER_SIZE;

    public Server() {
    }

    public Server(int PORT, int HEADER_SIZE, int BUFFER_SIZE) {
        this.PORT = PORT;
        this.HEADER_SIZE = HEADER_SIZE;
        this.BUFFER_SIZE = BUFFER_SIZE;
    }

    /**
     * Метод для запуска клиента.
     *
     * @apiNote создается Канал сервера для взаимодействия с клиентами (как для записи, так и для чтения) с хостом на
     * локальной машине и с прослушиваемым сервером портом
     * Сервер после создания находится в ожидании подключения клиента.
     * При подключении клиента создается канал клиента
     * Оба сервиса закрываются сами (try-with-resources).
     * Для разграничения пакетов и предотвращения deadlock'ов использован механизм заголовков: каждое получаемое
     * от сервера сообщение состоит из заголовка-числа (int размером 4 байта), указывающего количество байт сообщения и
     * непосредственно сообщения. Для этого создается 2 буфера: headerBuffer и messageBuffer.
     * При подключении клиента в буфер записывается приветственное сообщение, из буфера оно записывается в канал клиента
     * Пакеты от клиента через канал записываются в headerBuffer и messageBuffer, сообщение длиной, указанной в
     * headerBuffer читается из messageBuffer в массив байт, который преобразовывается в строку, обрабатываемую в
     * классе MathHandler.
     * При несоответствии формата примера шаблону, выбрасывается IllegalTaskFormatException, при обработке которого
     * клиенту направляется сообщение.
     * Бесконечный цикл завершается вводом в консоль ключевого слова "exit"
     */
    public void start() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
                .bind(new InetSocketAddress(PORT))) {
            System.out.println("Сервер запущен на порту " + PORT);

            try (SocketChannel clientSocketChannel = serverSocketChannel.accept()) {
                System.out.println("Клиент подключен, адрес " + clientSocketChannel.getRemoteAddress());

                ByteBuffer headerBuffer = ByteBuffer.allocate(HEADER_SIZE);
                ByteBuffer messageBuffer = ByteBuffer.allocate(HEADER_SIZE + BUFFER_SIZE);

                String greetingText = """
                        --------------------------------------------------------------------
                        Приветствую!
                        Эта программа - калькулятор, которая умеет складывать, вычетать,
                        умножать и делить.
                        Чтобы получить ответ введи числа и математическое действие:
                        сложить (+), вычесть (-), умножить (*) или разделить (/) между ними,
                        например: 3 * 5, и нажми enter.
                        Чтобы завершить программу, напиши exit
                        --------------------------------------------------------------------
                        """;

                sendMessage(clientSocketChannel, messageBuffer, greetingText);
                String inputMessage = readMessage(clientSocketChannel, headerBuffer, messageBuffer);
                while (!inputMessage.equals("exit")) {
                    try {
                        String taskAnswer = MathHandler.generateResult(inputMessage);
                        String reply = "Ответ: " + taskAnswer + "\n";
                        sendMessage(clientSocketChannel, messageBuffer, reply);
                    } catch (IllegalTaskFormatException e) {
                        sendMessage(clientSocketChannel, messageBuffer, e.getMessage());
                    }
                    inputMessage = readMessage(clientSocketChannel, headerBuffer, messageBuffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
