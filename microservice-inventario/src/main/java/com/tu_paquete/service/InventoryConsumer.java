package com.tu_paquete.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {

    @RabbitListener(queues = "inventoryQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // Lógica para procesar el mensaje recibido
    }
}
