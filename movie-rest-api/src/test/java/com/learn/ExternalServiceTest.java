package com.learn;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExternalServiceTest {

    @Test
    void returnSameResponseForAnyCalls() {
        ExternalService externalService = mock(ExternalService.class);

        when(externalService.getFullCountryName(any(String.class))).thenReturn("Random");

        assertEquals("Random", externalService.getFullCountryName("random"));
        assertEquals("Random", externalService.getFullCountryName("nustiu"));
    }
    @Test
    void returnDifferentResponseForConsecutiveCalls() {
        ExternalService externalService = mock(ExternalService.class);

        when(externalService.getFullCountryName(any(String.class))).thenReturn("Random", "Nustiu");

        assertNotEquals(externalService.getFullCountryName("123"), externalService.getFullCountryName("123"));
    }

    @Test
    void returnResponseBasedOnParameter() {
        ExternalService externalService = mock(ExternalService.class);

        when(externalService.getFullCountryName(any(String.class))).thenAnswer(
                request -> {
                    if (request.getArgument(0).equals("MD")) {
                        return "Moldova";
                    } else if (request.getArgument(0).equals("US")) {
                        return "United States";
                    } else {
                        return "Rest of the world";
                    }
                }
        );

        assertEquals("Moldova", externalService.getFullCountryName("MD"));
        assertEquals("United States", externalService.getFullCountryName("US"));
    }

    @Test
    void shouldThrowException() {
        ExternalService externalService = mock(ExternalService.class);

        when(externalService.getFullCountryName(any(String.class))).thenThrow(new RuntimeException("not found"));

        assertThrows(RuntimeException.class, () -> externalService.getFullCountryName("random"));
    }

    @Test
    void spyTest() {
        ExternalService externalService = spy(ExternalService.class);

        Map<String, String> countries = new HashMap<>();
        countries.put("MD", "Maldive");

        when(externalService.findAll()).thenReturn(countries);

        assertEquals("Maldive", externalService.getFullCountryName("MD"));
    }

    @Test
    void anotherSpyTest() {
        ExternalService externalService = spy(ExternalService.class);

        assertEquals(3, externalService.sum(1, 2));
    }
}
