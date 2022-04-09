package com.example.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    /*@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion
            (@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class, map);
        CurrencyConversion result = responseEntity.getBody();
        return new CurrencyConversion(result.getId(), from, to, quantity, result.getConversionMultiple(),
                quantity.multiply(result.getConversionMultiple()), result.getEnvironment());
    }*/

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion
            (@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
        CurrencyConversion result = proxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(result.getId(), from, to, quantity, result.getConversionMultiple(),
                quantity.multiply(result.getConversionMultiple()), result.getEnvironment());
    }
}
