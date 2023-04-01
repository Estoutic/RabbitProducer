package com.example.rabbitproducer.services.impl;

import com.example.rabbitproducer.services.RabbitMQProducerService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message, String key) {
        rabbitTemplate.convertAndSend("generated", key,message);
    }
    @Override
    public void sendFileToQueue(byte[] fileBytes) {
        Message message =  MessageBuilder
                .withBody(fileBytes)
                .setContentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .setHeader("metaInfoId","41174782-91c8-4e38-8a8b-e43319beff58")
                .setHeader("file_name", "example.txt")
                .build();
        rabbitTemplate.convertAndSend("generated",null, message);
    }
}
