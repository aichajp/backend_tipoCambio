/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.edu.gt.progra2.entidad;


import java.time.LocalDateTime;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;



/**
 *
 * @author JB
 */
@Service
public class TipoCambioSoapService {
    private final TipoCambioRepository tipoCambioRepository;
    private final SolicitudLogRepository solicitudLogRepository;

    public TipoCambioSoapService(TipoCambioRepository tipoCambioRepository, SolicitudLogRepository solicitudLogRepository) {
        this.tipoCambioRepository = tipoCambioRepository;
        this.solicitudLogRepository = solicitudLogRepository;
    }

    TipoCambio obtenerYGuardarTipoCambio() {
        String soapEndpoint = "https://banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL";
        String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";

        String soapRequest = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                             "<soap:Body><TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />" +
                             "</soap:Body></soap:Envelope>";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", soapAction);
        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(soapEndpoint, HttpMethod.POST, request, String.class);
            String responseBody = response.getBody();
            
            // Extracción de tipo de cambio de la respuesta SOAP
            Double tipoCambio = parseTipoCambio(responseBody);

            // Guardar en la base de datos
            TipoCambio nuevoTipoCambio = new TipoCambio();
            nuevoTipoCambio.setFechaSolicitud(LocalDateTime.now());
            nuevoTipoCambio.setTipoCambio(tipoCambio);
            tipoCambioRepository.save(nuevoTipoCambio);

            // Guardar el log de la solicitud
            SolicitudLog log = new SolicitudLog();
            log.setNumeroSolicitud(UUID.randomUUID().toString());
            log.setRespuestaSoap(responseBody);
            log.setFechaSolicitud(LocalDateTime.now());
            solicitudLogRepository.save(log);

            return nuevoTipoCambio;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Double parseTipoCambio(String soapResponse) {
        // Aquí se debe extraer el tipo de cambio desde la respuesta SOAP
        // Esto dependerá del formato exacto de la respuesta SOAP del Banco de Guatemala
        return 7.8;  // Placeholder, ajusta según la respuesta real
    }

    
}
