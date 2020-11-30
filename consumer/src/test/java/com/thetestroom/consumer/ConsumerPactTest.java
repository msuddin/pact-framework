package com.thetestroom.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.thetestroom.consumer.model.ConsumerObject;
import com.thetestroom.consumer.service.ConsumerService;
import org.junit.Rule;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConsumerPactTest {

    @Rule
    public PactProviderRuleMk2 mockProvider
            = new PactProviderRuleMk2("test_provider", "localhost", 8083, this);

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder
            .uponReceiving("GET REQUEST")
                .path("/provider-hello")
                .method("GET")
            .willRespondWith()
                .status(200)
                .body("provider is alive")
            .uponReceiving("POST REQUEST")
                .method("POST")
                .headers("content-type", "application/json")
                .body("{\"name\": \"consumer\"}")
                .path("/provider-hello")
            .willRespondWith()
                .status(201)
                .body("{\"name\": \"consumer\"}")
            .toPact();
    }

    @Test
    @PactVerification()
    public void shouldGetCorrectResponse() {
        // Given
        ConsumerService providerService = new ConsumerService();

        // When
        String getResponse = providerService.getStringResponseEntity();
        ResponseEntity<String> postResponse = providerService.getStringResponseEntity(new ConsumerObject("consumer"));

        // Then
        assertThat(getResponse, containsString("provider is alive"));
        assertThat(postResponse.getBody(), containsString("consumer"));
    }
}
