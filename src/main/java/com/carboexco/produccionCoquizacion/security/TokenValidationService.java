package com.carboexco.produccionCoquizacion.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Data
public class TokenValidationService {

    private String bearerToken;
    private final String validationUrl = "http://localhost:8084/validate";

    public ResponseEntity<String> callValidateTokenEndpoint() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Construye los encabezados de la solicitud HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", bearerToken);

            // Realiza la llamada al endpoint /validate
            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    validationUrl,
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );

            // Obtiene la respuesta de la validación del token
            String responseBody = response.getBody();
            int statusCode = response.getStatusCodeValue();

            return response;
        }catch (Exception e){
            return null;
        }

    }
}
