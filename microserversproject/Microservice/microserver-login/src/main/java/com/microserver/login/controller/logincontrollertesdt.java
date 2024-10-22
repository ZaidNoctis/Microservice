package com.microserver.login.controller;

import com.microserver.login.entities.Login;
import com.microserver.login.service.ILoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginControllerTest {

    @Mock
    private ILoginService loginService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    public void testRegisterUser() {
        // Datos de prueba
        Login login = new Login();
        login.setUsername("testuser");
        login.setPassword("password");

        // Comportamiento esperado del servicio
        when(loginService.save(any(Login.class))).thenReturn(login);

        // Llamar al método a probar
        ResponseEntity<Login> response = loginController.register(login);

        // Verificar que el servicio fue llamado
        verify(loginService).save(login);

        // Verificar que se envió un mensaje a RabbitMQ
        verify(rabbitTemplate).convertAndSend(eq("ordersQueue"), eq("User registered: testuser"));

        // Comprobar el estado de la respuesta
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUsername()).isEqualTo("testuser");
    }
}
