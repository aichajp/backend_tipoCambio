package com.umg.edu.gt.progra2.HelloWorld.Controller;
import com.umg.edu.gt.progra2.HelloWorld.Dto.TipoCambio;
import com.umg.edu.gt.progra2.HelloWorld.service.TipoCambioSoapService;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoCambioController {

    @Autowired
    private TipoCambioSoapService tipoCambioSoapService;

    @GetMapping("/tipoCambioDia")
    public ResponseEntity<Object> obtenerTipoCambioDia() {
        String tipoCambio = tipoCambioSoapService.obtenerTipoCambioDia();

        // Verificar si la respuesta contiene errores
        if (tipoCambio.contains("Error")) {
            return ResponseEntity.status(500).body("Error: " + tipoCambio);
        }

        try {
            // Convertir el XML a JSON
            JSONObject xmlJSONObj = XML.toJSONObject(tipoCambio);

            // datos específicos del JSON
            JSONObject varDolar = xmlJSONObj.getJSONObject("soap:Envelope")
                    .getJSONObject("soap:Body")
                    .getJSONObject("TipoCambioDiaResponse")
                    .getJSONObject("TipoCambioDiaResult")
                    .getJSONObject("CambioDolar")
                    .getJSONObject("VarDolar");

            // traer los valores que te interesan
            String fecha = varDolar.getString("fecha");
            double referencia = varDolar.getDouble("referencia");

            // se creo la instancia del TipoCambio
            TipoCambio tipoCambioDTO = new TipoCambio(fecha, referencia);

            // Devolver la respuesta con el TipoCambio
            return ResponseEntity.ok(tipoCambioDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el XML.");
        }
        
        
    }
   
    
    
}
