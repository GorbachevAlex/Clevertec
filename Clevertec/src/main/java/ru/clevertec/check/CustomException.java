package main.java.ru.clevertec.check;

public class CustomException extends Exception {
    private final String messageType;

    public CustomException(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
