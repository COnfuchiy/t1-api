package ru.t1.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MessageResponse {
    private final String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static MessageResponse fromHttpResponse(String response) {
        return new MessageResponse(response);
    }

    public String getMessage() {
        return message;
    }
}
