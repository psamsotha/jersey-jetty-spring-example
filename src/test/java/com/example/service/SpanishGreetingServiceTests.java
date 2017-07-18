package com.example.service;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Paul Samsotha.
 */
public class SpanishGreetingServiceTests {

    private SpanishGreetingService service;


    @Before
    public void setUp() {
        this.service = new SpanishGreetingService();
    }

    @Test
    public void testSpanishHelloMessage() {
        final String msg = this.service.getGreeting("Paulo");

        assertThat(msg).isEqualTo("Hola, Paulo!");
    }
}
