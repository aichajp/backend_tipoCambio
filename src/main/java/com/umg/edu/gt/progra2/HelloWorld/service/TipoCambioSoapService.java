package com.umg.edu.gt.progra2.HelloWorld.service;
import com.umg.edu.gt.progra2.HelloWorld.model.TipoCambio;
import com.umg.edu.gt.progra2.HelloWorld.repository.TipoCambioRepository;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;



@Service
public class TipoCambioSoapService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    // Método para obtener y guardar el tipo de cambio
    public TipoCambio obtenerYGuardarTipoCambioDia() {
        // Código para obtener el tipo de cambio del servicio SOAP y guardarlo en la base de datos
        String soapEndpoint = "https://banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL";
        String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";
        String soapRequest = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
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

            if (result != null) {
                JSONObject xmlJSONObj = XML.toJSONObject(result);
                JSONObject varDolar = xmlJSONObj.getJSONObject("soap:Envelope")
                        .getJSONObject("soap:Body")
                        .getJSONObject("TipoCambioDiaResponse")
                        .getJSONObject("TipoCambioDiaResult")
                        .getJSONObject("CambioDolar")
                        .getJSONObject("VarDolar");

                String fecha = varDolar.getString("fecha");
                double referencia = varDolar.getDouble("referencia");

                TipoCambio tipoCambio = new TipoCambio();
                tipoCambio.setFecha(fecha);
                tipoCambio.setReferencia(referencia);

                // Guarda el tipo de cambio en la base de datos
                tipoCambioRepository.save(tipoCambio);
                return tipoCambio;
            } else {
                throw new RuntimeException("Error al obtener el tipo de cambio: Respuesta vacía del servicio SOAP");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el tipo de cambio: " + e.getMessage());
        }
    }

    // Programar la ejecución del método obtenerYGuardarTipoCambioDia cada hora
    @Scheduled(fixedRate = 3600000) // cada 3600000 ms = 1 hora
    public void programarObtencionTipoCambio() {
        obtenerYGuardarTipoCambioDia();
    }
}