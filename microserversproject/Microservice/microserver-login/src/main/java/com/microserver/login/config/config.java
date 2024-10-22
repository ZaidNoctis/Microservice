package com.microserver.login.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "ordersQueue"; // Nombre de la cola

    @Bean
    public Queue ordersQueue() {
        return new Queue(ordersQueue, true); // La cola es durable
    }
}

