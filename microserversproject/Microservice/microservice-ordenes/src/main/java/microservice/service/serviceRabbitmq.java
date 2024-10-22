package microservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ServiceRabbitmq {

    @RabbitListener(queues = "ordersQueue")
    public void receiveLoginMessage(String message) {
        System.out.println("Received message from login service: " + message);
        // Lógica para manejar el mensaje, por ejemplo, verificar usuario
    }

}
