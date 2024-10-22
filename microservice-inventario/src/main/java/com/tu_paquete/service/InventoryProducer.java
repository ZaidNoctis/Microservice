package com.tu_paquete.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String queueName = "inventoryQueue";

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
