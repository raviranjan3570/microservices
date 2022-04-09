package com.example.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
* RateLimiter limits number of calls to particular microservice
* BulkHead tells you how many concurrent calls to particular microservice is allowed.
*/

@RestController
public class CircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String sampleApi(){
        logger.info("Sample api call received");
        ResponseEntity<String> response =
                new RestTemplate().getForEntity("http://localhost:8080/random-api", String.class);
        return response.getBody();
    }

    public String hardCodedResponse(Exception exception){
        return "fallback response";
    }
}
