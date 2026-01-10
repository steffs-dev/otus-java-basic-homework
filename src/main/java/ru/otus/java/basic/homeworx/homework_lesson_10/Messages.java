package ru.otus.java.basic.homeworx.homework_lesson_10;

/**
 * Интерфейс с текстами сообщений, выводимых в консоль
 */
public interface Messages {
    String welcomeMessage = "\nHi, the box is created!\n";

    String listOfCommands = """
            To work with the box, use the following commands:
            * open - to open the box;
            * close - to close the box;
            * info - to get information about the box;
            * colour_ - to change the colour of the box (write new colour after underscore, e.g. colour_white);
            * put_ - to place an item to the box (write an item after underscore, e.g. put_pencil);
            Note that the box shall be opened and empty.
            * take - to take an item from the box. Note that the box shall be opened and NOT empty;
            * stop - to stop all the operations with the box;
            * help - to get the list of the commands;
            """;

    String boxInfo = """
            Box Information:
            \t size: %d,
            \t colour: %s,
            \t itemInside: %s,
            \t isBoxOpened: %b
            \t isBoxEmpty: %b
                        
            """;

    String setColour = "Colour of the Box has been successfully changed. Current colour is %s";

    String boxOpened = "Box's opened";

    String boxClosed = "Box's closed";

    String boxIsFullAndClosedWarn = "The box's full and closed. Take an item from the box and open it first";

    String boxIsEmptyAndClosedWarn = "The box's empty and closed. Place an item into the box and open it first";

    String boxIsFullWarn = "The box's full. Take an item from the box first";

    String boxIsEmptyWarn = "The box's empty. Place an item into the box first";

    String boxIsClosedWarn = "The box's closed. First, you need to open it";

    String itemPlacedInBox = "The following item is placed into the box: %s";

    String itemRemovedFromBox = "The following item is removed from the box: %s";

    String enterCommand = "Enter command: ";

    String unknownCommand = "Unknown command. Please check the list of commands and try again.";

    String stopWorking = "Good buy. See you soon";
}
