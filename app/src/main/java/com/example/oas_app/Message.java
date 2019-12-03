package com.example.oas_app;

public class Message {
    String text;
    boolean belongToCurrentUser;

    public Message(String text, boolean belongToCurrentUser) {
        this.text = text;
        this.belongToCurrentUser = belongToCurrentUser;
    }
}
