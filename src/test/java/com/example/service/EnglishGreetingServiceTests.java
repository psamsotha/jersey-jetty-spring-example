package com.example.service;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Paul Samsotha.
 */
public class EnglishGreetingServiceTests {

    private EnglishGreetingService service;


    @Before
    public void setUp() {
        this.service = new EnglishGreetingService();
    }

    @Test
    public void testEnglishHelloMessage() {
        final String msg = this.service.getGreeting("Paul");

        assertThat(msg).isEqualTo("Hello, Paul!");
    }
}
