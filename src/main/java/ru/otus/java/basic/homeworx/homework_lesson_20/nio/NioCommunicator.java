package ru.otus.java.basic.homeworx.homework_lesson_20.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Интерфейс, содержащий методы для обработки входящих и исходящих пакетов, общие для сервера и клиента
 * PORT - константа порта, который прослушивает сервер
 * HEADER_SIZE - константа размера буфера заголовков
 * BUFFER_SIZE - константа размера буфера сообщений
 */
public interface NioCommunicator {
    int PORT = 8080;
    int HEADER_SIZE = 4;
    int BUFFER_SIZE = 1020;

    /**
     * Метод для чтения пакета.
     * @apiNote
     * Для разграничения пакетов и предотвращения deadlock'ов использован механизм заголовков: каждое получаемое
     * от сервера сообщение состоит из заголовка-числа (int размером 4 байта), указывающего количество байт сообщения и
     * непосредственно сообщения. Для этого создается 2 буфера: headerBuffer и messageBuffer.
     * Порядок работы:
     *  1) из канала клиента в headerBuffer читается часть полученного пакета длиной HEADER_SIZE - это длина сообщения.
     *  2) если длина сообщения больше размера messageBuffer, создается временный буфер, равный длине сообщения (ввиду
     *  специфики приложение, не предполагается, что операция будет частой)
     *  3) сообщение записывается в буфер
     *  4) создается массив байт, длиной, равной длине сообщения
     *  5) сообщение из буфера записывается в массив байт
     *  6) массив байт преобразовывается в строку
     * @param clientSocketChannel - канал клиента
     * @param headerBuffer - буфер заголовка
     * @param messageBuffer - буфер сообщения
     * @return строка, содержащая сообщение
     * @throws IOException
     */
    default String readMessage(SocketChannel clientSocketChannel, ByteBuffer headerBuffer, ByteBuffer messageBuffer)
            throws IOException {
        readCertainLength(clientSocketChannel, headerBuffer, HEADER_SIZE);
        int length = headerBuffer.getInt();
        ByteBuffer buffer = sizedBuffer(messageBuffer, length);
        readCertainLength(clientSocketChannel, buffer, length);

        byte[] message = new byte[length];
        buffer.get(message);
        buffer.clear();
        return new String(message, StandardCharsets.UTF_8);
    }

    /**
     * Метод для чтения сообщения определенной длины из канала клиента в буфер
     * @param clientSocketChannel - канал клиента
     * @param buffer - буфер, в который будет осуществляться чтение
     * @param length - длин сообщения, в байтах
     * @throws IOException
     */
    private void readCertainLength(SocketChannel clientSocketChannel, ByteBuffer buffer, int length)
            throws IOException {
        buffer.clear();
        int total = 0;
        while (total < length) {
            int read = clientSocketChannel.read(buffer);
            total += read;
        }
        buffer.flip();
    }

    /**
     * Метод для отправки пакета
     * @apiNote
     * Для разграничения пакетов и предотвращения deadlock'ов использован механизм заголовков: каждое получаемое
     * от сервера сообщение состоит из заголовка-числа (int размером 4 байта), указывающего количество байт сообщения и
     * непосредственно сообщения. Для этого создается 2 буфера: headerBuffer и messageBuffer.
     * Порядок работы:
     * 1) создается массив байт, наполняемый байтовым представлением строки
     * 2) если длина сообщения + HEADER_SIZE больше размера messageBuffer, создается временный буфер, равный длине
     * сообщения + HEADER_SIZE (ввиду специфики приложение, не предполагается, что операция будет частой)
     * 3) в буфер добавляется заголовок - 4 байта, представляющие число - длину сообщения
     * 4) в буфер добавляется сообщение
     * 5) содержимое буфера записывается в канал клиента
     * @param clientSocketChannel - канал клиента
     * @param messageBuffer - буфер
     * @param text - строковое представление сообщения
     * @throws IOException
     */
    default void sendMessage(SocketChannel clientSocketChannel, ByteBuffer messageBuffer, String text)
            throws IOException {
        messageBuffer.clear();
        byte[] message = text.getBytes(StandardCharsets.UTF_8);
        int length = message.length;
        ByteBuffer buffer = sizedBuffer(messageBuffer, length + HEADER_SIZE);

        buffer.putInt(length);
        buffer.put(message);
        buffer.flip();
        while (buffer.hasRemaining()) {
            clientSocketChannel.write(buffer);
        }
    }

    /**
     * Метод для проверки соответствия размера заданного буфера размеру помещаемого в него содержимого.
     * При недостаточности размера заданного буфера создается новый временный буфер заданного размера
     * @param messageBuffer - дефолтный буфер сообщений
     * @param length - длина содержимого, которое необходимо поместить в буфер
     * @return дефолтный буфер, если его размер <= length, в ином случае - временный буфер, размером = length
     */
    private ByteBuffer sizedBuffer(ByteBuffer messageBuffer, int length) {
        if (length > messageBuffer.capacity()) {
            return ByteBuffer.allocate(length);
        }
        return messageBuffer;
    }

}
