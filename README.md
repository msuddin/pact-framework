# PACT Framework

The purpose of this framework is to provide an example of using PACT JVM.

## Approach

This project contains two API's. It contains
 - consumer api (localhost:8082)
 - provide api (localhost:8083)
 
 The consumer API makes a call to the provider API via to endpoints.
 
 ### Endpoint Mapping
  - GET localhost:8082/consumer-hello -> localhost:8083/provider-hello
  - POST localhost:8082/consumer-hello -> localhost:8083/provider-hello
  
POST body payload:
```
{
    "name": "mo"
}
```

## PACT Testing - Instructions

 - First, ensure that no API's are running.
 - Run the pact test in consumer api under .../test/...
 - This will generate a pact file in consumer/target/pacts/
 - Copy the file into provider/pacts/
 - Run the pact test in provider api under .../test/...
 
When a consumer pact test is executed, it generates a pact file and uses the pact file as a mock for all pact tests.

We then copy over that pact file into the pact folder for provider api and run it's pact tests.

Provider api runs an instance of itselfs anc confirms that the api and the pact file mirror each other. 
