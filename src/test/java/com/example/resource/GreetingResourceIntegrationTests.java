package com.example.resource;

import com.example.SpringConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.jetty.JettyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Paul Samsotha.
 */
public class GreetingResourceIntegrationTests extends JerseyTest {

    @Override
    public TestContainerFactory getTestContainerFactory() throws TestContainerException {
        return new JettyTestContainerFactory();
    }

    @Override
    public ResourceConfig configure() {
        final ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        return new ResourceConfig()
                .register(GreetingResource.class)
                .property("contextConfig", context);
    }

    @Test
    public void testEnglishGreetingEndpoint() {
        final Response response = target("/greeting/en/Paul").request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(String.class)).isEqualTo("Hello, Paul!");
    }

    @Test
    public void testSpanishGreetingEndpoint() {
        final Response response = target("/greeting/es/Paulo").request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(String.class)).isEqualTo("Hola, Paulo!");
    }

    @Test
    public void testNotFoundEndpoint() {
        final Response response = target("/greeting/fr/Paul").request().get();

        assertThat(response.getStatus()).isEqualTo(404);
    }
}
