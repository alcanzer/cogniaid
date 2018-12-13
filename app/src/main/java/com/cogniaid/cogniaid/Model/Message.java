package com.cogniaid.cogniaid.Model;

/**
 * Created by alcanzer on 12/8/18.
 */

public class Message {
    String message;
    boolean isBot;

    public Message(String message, boolean isBot){
        this.message = message;
        this.isBot = isBot;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }
}
