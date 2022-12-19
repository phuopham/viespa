package com.spa.viespa.repositories;

public interface EmailSender {
    void send(String to, String email);
}
