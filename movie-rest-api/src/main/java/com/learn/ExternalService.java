package com.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExternalService {

    Map<String, String> countries = new HashMap<>();

    public ExternalService() {
        countries.put("US", "United States");
        countries.put("UK", "United Kingdom");
        countries.put("MD", "Moldova");
    }

    public Map<String, String> findAll() {
        return countries;
    }

    public String getFullCountryName(String code) {
        return Optional.of(findAll().get(code)).orElseThrow(() -> new RuntimeException("not found"));
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
