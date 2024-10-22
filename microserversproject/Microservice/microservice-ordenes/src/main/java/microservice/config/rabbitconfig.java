package microservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Define el nombre de la cola
    public static final String QUEUE_NAME = "ordersQueue";

    // Crea la cola de RabbitMQ
    @Bean
    public Queue ordersQueue() {
        return new Queue(QUEUE_NAME, true); // true significa que la cola es durable
    }
}
