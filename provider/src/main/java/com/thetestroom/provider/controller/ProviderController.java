package com.thetestroom.provider.controller;

import com.thetestroom.provider.model.ProviderObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProviderController {

    @GetMapping(value = "/provider-hello")
    public String isAlive() {
        return "provider is alive";
    }

    @PostMapping(value = "/provider-hello", consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderObject getProviderObject(@RequestBody ProviderObject providerObject) {
        return new ProviderObject(providerObject.getName());
    }
}
