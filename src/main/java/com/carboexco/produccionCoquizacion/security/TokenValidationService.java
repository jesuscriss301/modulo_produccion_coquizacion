package com.carboexco.produccionCoquizacion.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;

@AllArgsConstructor
public class TokenValidationService {

    private String token;
    private final String validationUrl = "http://localhost:8084/validate";

    public ResponseEntity<String> validateToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Puedes ajustar el tipo de solicitud (GET, POST, etc.) según tu necesidad
        ResponseEntity<String> response = restTemplate.exchange(validationUrl, HttpMethod.GET, null, String.class);

        return response;
    }
}
