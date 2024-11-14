package com.umg.edu.gt.progra2.HelloWorld;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author JB
 */
import com.umg.edu.gt.progra2.HelloWorld.service.TipoCambioSoapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSoap {

    @Autowired
    private TipoCambioSoapService tipoCambioSoapService;

    @Test
    public void testObtenerTipoCambioDia() {
        // Realiza aquí la llamada a tipoCambioSoapService y las aserciones necesarias.
        tipoCambioSoapService.obtenerTipoCambioDia();
    }
}