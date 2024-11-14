package com.umg.edu.gt.progra2.HelloWorld.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoCambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha;
    private double referencia;

    // Constructor vacío
    public TipoCambio() {}

    // Constructor con parámetros
    public TipoCambio(String fecha, double referencia) {
        this.fecha = fecha;
        this.referencia = referencia;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getReferencia() {
        return referencia;
    }

    public void setReferencia(double referencia) {
        this.referencia = referencia;
    }
}
