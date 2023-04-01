package com.example.rabbitproducer.controllers;

import com.example.rabbitproducer.controllers.models.DataDto;
import com.example.rabbitproducer.services.RabbitMQProducerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping("/file")
    public void postFile(@RequestParam("file") MultipartFile rawFile) throws IOException {
        rabbitMQProducerService.sendFileToQueue(rawFile.getBytes());
    }
}
