package com.example.restapiproduct.entity;

import java.io.Serializable;

public class Message implements Serializable {
    public String getMessage() {
        return message;
    }

    public Message(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
