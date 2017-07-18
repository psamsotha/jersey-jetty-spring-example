package com.example.service;

import org.springframework.stereotype.Service;

/**
 * @author Paul Samsotha.
 */
@Service
public class EnglishGreetingService implements GreetingService {
    @Override
    public String getGreeting(String name) {
        return "Hello, " + name + "!";
    }
}
