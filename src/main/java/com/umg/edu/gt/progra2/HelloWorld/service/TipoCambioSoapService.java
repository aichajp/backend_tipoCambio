package com.umg.edu.gt.progra2.HelloWorld.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Service
public class TipoCambioSoapService {

    public String obtenerTipoCambioDia() {
        String soapEndpoint = "https://banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL";
        String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";

        String soapRequest =
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                        "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                        "<soap:Body>" +
                        "<TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />" +
                        "</soap:Body>" +
                        "</soap:Envelope>";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", soapAction);

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);

        try {
            String result = restTemplate.exchange(soapEndpoint, HttpMethod.POST, request, String.class).getBody();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            // registrar el error o lanza una excepción mas detallada
            return "Error al obtener el tipo de cambio: " + e.getMessage();
        }
    }
}
