package com.example.rabbitproducer.services;

public interface RabbitMQProducerService {

    public void sendMessage(String message, String key);
}
