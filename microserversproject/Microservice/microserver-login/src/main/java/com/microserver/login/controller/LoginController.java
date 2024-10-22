package com.microserver.login.controller;

import com.microserver.login.entities.Login;
import com.microserver.login.service.ILoginService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final ILoginService loginService;
    private final RabbitTemplate rabbitTemplate; // RabbitMQ Template

    @Autowired
    public LoginController(ILoginService loginService, RabbitTemplate rabbitTemplate) {
        this.loginService = loginService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/register")
    public ResponseEntity<Login> register(@RequestBody Login login) {
        Login savedLogin = loginService.save(login);

        // Enviar un mensaje a la cola después de registrar
        rabbitTemplate.convertAndSend("ordersQueue", "User registered: " + login.getUsername());

        return ResponseEntity.ok(savedLogin);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Login> findByUsername(@PathVariable String username) {
        Optional<Login> login = loginService.findByUsername(username);
        return login.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/register")
    public ResponseEntity<Login> register(@RequestBody Login login) {
        Login savedLogin = loginService.save(login);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, login.getUsername()); // Envía el username
        return ResponseEntity.ok(savedLogin);
    }

}