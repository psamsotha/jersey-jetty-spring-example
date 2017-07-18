package com.example.service;

/**
 * @author Paul Samsotha.
 */
public class SpanishGreetingService implements GreetingService {
    @Override
    public String getGreeting(String name) {
        return "Hola, " + name + "!";
    }
}
