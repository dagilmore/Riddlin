package com.riddler.app.message;

public final class Message {

    private final MessageType type;

    private final String text;

    /**
     * Creates a new Message of a certain type consisting of the text provided.
     */
    public Message(MessageType type, String text) {
        this.type = type;
        this.text = text;
    }

    /**
     * The type of message; such as info, warning, error, or success.
     */
    public MessageType getType() {
        return type;
    }

    /**
     * The message text.
     */
    public String getText() {
        return text;
    }

    public String getCssClass() {
        return type.getCssClass();
    }
    
    public String toString() {
        return type + ": " + text;
    }

}
