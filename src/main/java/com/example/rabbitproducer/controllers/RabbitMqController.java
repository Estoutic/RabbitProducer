package com.example.rabbitproducer.controllers;

import com.example.rabbitproducer.controllers.models.DataDto;
import com.example.rabbitproducer.services.RabbitMQProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {

    private final RabbitMQProducerService rabbitMQProducerService;

    public RabbitMqController(RabbitMQProducerService rabbitMQProducerService) {
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    @GetMapping("/send")
    public void sendMessage(@RequestBody()DataDto dataDto){
        rabbitMQProducerService.sendMessage(dataDto.getMessage(),dataDto.getKey());
    }
    @GetMapping("/version")
    public String healthCheck() {
        return "1.0";
    }
}
