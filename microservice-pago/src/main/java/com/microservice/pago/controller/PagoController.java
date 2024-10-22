package com.microservice.pago.controller;

import com.microservice.pago.entities.Pago;
import com.microservice.pago.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    @GetMapping
    public List<Pago> findAll() {
        return pagoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> findById(@PathVariable Long id) {
        Pago pago = pagoService.findById(id);
        if (pago != null) {
            return new ResponseEntity<>(pago, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pago> create(@RequestBody Pago pago) {
        Pago newPago = pagoService.save(pago);
        return new ResponseEntity<>(newPago, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable Long id, @RequestBody Pago pagoDetails) {
        Pago pago = pagoService.findById(id);
        if (pago != null) {
            pago.setMonto(pagoDetails.getMonto());
            pago.setFechaPago(pagoDetails.getFechaPago());
            pago.setMetodoPago(pagoDetails.getMetodoPago());
            pago.setEstado(pagoDetails.getEstado());
            return new ResponseEntity<>(pagoService.save(pago), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        pagoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
