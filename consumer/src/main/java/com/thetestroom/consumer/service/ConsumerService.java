package com.thetestroom.consumer.service;

import com.thetestroom.consumer.model.ConsumerObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    String url = "http://localhost:8083/provider-hello";
    RestTemplate restTemplate = new RestTemplate();

    public String getStringResponseEntity() {
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    public ResponseEntity<String> getStringResponseEntity(@RequestBody ConsumerObject providerObject) {
        providerObject.setName(providerObject.getName());
        return restTemplate.postForEntity(url, providerObject, String.class);
    }

}
