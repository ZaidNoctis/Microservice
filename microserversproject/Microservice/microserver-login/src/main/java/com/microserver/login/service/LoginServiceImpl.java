package com.microserver.login.service;

import com.microserver.login.entities.Login;
import com.microserver.login.persistence.LoginRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements ILoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Optional<Login> findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    @Override
    public Login save(Login login) {
        return loginRepository.save(login);
    }

    // Nuevo método para recibir mensajes de la cola
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveOrder(String username) {
        System.out.println("Received order for user: " + username);
        Optional<Login> user = loginRepository.findByUsername(username);
        if (user.isPresent()) {
            System.out.println("User found: " + username);
        } else {
            System.out.println("User not found: " + username);
        }
    }
}
