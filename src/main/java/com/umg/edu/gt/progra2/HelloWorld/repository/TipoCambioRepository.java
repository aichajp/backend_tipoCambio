/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.edu.gt.progra2.HelloWorld.repository;

/**
 *
 * @author JB
 */
import com.umg.edu.gt.progra2.HelloWorld.model.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {
    TipoCambio findTopByOrderByIdDesc(); // Para obtener el último registro
}