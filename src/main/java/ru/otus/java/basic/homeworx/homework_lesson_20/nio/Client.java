package ru.otus.java.basic.homeworx.homework_lesson_20.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Класс клиента с использованием пакета nio
 * PORT - константа порта, который прослушивает сервер. By default - значения из интерфейса NioCommunicator. Может быть задан при создании экземпляра класса
 * HEADER_SIZE - константа размера буфера заголовков. By default - значения из интерфейса NioCommunicator. Может быть задан при создании экземпляра класса
 * BUFFER_SIZE - константа размера буфера сообщений. By default - значения из интерфейса NioCommunicator. Может быть задан при создании экземпляра класса
 * Класс имплементиентирует интерфейс NioCommunicator, содержащий все методы для работы с пакетами (общие как для клиента, так и для сервера)
 */
public class Client implements NioCommunicator {
    private int PORT = NioCommunicator.PORT;
    private int HEADER_SIZE = NioCommunicator.HEADER_SIZE;
    private int BUFFER_SIZE = NioCommunicator.BUFFER_SIZE;

    public Client() {
    }

    public Client(int PORT, int HEADER_SIZE, int BUFFER_SIZE) {
        this.PORT = PORT;
        this.HEADER_SIZE = HEADER_SIZE;
        this.BUFFER_SIZE = BUFFER_SIZE;
    }

    /**
     * Метод для запуска клиента.
     *
     * @apiNote создается Канал для взаимодействия с сервером (как для записи, так и для чтения) с хостом на локальной
     * машине и с прослушиваемым сервером портом
     * Для чтения из консоли создается Scanner.
     * Оба сервиса закрываются сами (try-with-resources).
     * Для разграничения пакетов и предотвращения deadlock'ов использован механизм заголовков: каждое получаемое
     * от сервера сообщение состоит из заголовка-числа (int размером 4 байта), указывающего количество байт сообщения и
     * непосредственно сообщения. Для этого создается 2 буфера: headerBuffer и messageBuffer.
     * При работе в буфер читается введенный в консоль пример, из буфера он читается в канал.
     * Пакеты от сервера через канал записываются в headerBuffer и messageBuffer, сообщение длиной, указанной в
     * headerBuffer читается из messageBuffer и выводится в консоль.
     * Бесконечный цикл завершается вводом в консоль ключевого слова "exit"
     */
    public void start() {
        try (SocketChannel clientSocketChannel = SocketChannel.open();
             Scanner consoleReader = new Scanner(System.in);
        ) {
            clientSocketChannel.connect(new InetSocketAddress("localhost", PORT));
            ByteBuffer headerBuffer = ByteBuffer.allocate(HEADER_SIZE);
            ByteBuffer messageBuffer = ByteBuffer.allocate(HEADER_SIZE + BUFFER_SIZE);

            System.out.println(readMessage(clientSocketChannel, headerBuffer, messageBuffer));
            while (true) {
                String consoleMessage = consoleReader.nextLine();
                sendMessage(clientSocketChannel, messageBuffer, consoleMessage);
                if (consoleMessage.equals("exit")) {
                    System.out.println("До свидания!");
                    break;
                }
                System.out.println(readMessage(clientSocketChannel, headerBuffer, messageBuffer));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
