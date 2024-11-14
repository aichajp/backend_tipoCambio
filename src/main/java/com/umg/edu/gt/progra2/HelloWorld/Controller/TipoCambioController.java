package com.umg.edu.gt.progra2.HelloWorld.Controller;

import com.umg.edu.gt.progra2.HelloWorld.model.TipoCambio;
import com.umg.edu.gt.progra2.HelloWorld.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TipoCambioController {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @GetMapping("/tipoCambioDia")
    public ResponseEntity<TipoCambio> obtenerTipoCambioDia() {
        // Obtener el último registro del tipo de cambio en la base de datos
        TipoCambio tipoCambio = tipoCambioRepository.findTopByOrderByIdDesc();

        // Retornar el tipo de cambio en formato JSON
        return ResponseEntity.ok(tipoCambio);
    }}
