package com.thetestroom.consumer.api;

import com.thetestroom.consumer.model.ConsumerObject;
import com.thetestroom.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService providerService;

    @GetMapping(value = "/consumer-hello")
    public String isAlive() {
        return providerService.getStringResponseEntity();
    }

    @PostMapping(value = "/consumer-hello", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getProviderObject(@RequestBody ConsumerObject providerObject) {
        return providerService.getStringResponseEntity(providerObject);
    }


}
