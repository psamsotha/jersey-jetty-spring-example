package com.example;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Paul Samsotha.
 */
public class MainTests {

    private final int PORT = 9090;

    public Server server;


    @Before
    public void setUp() throws Exception {
        this.server = Main.getApplicationServer(PORT);
        this.server.start();
    }

    @After
    public void tearDown() throws Exception {
        this.server.stop();
    }

    @Test
    public void testServerJerseyConfiguration() {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://localhost:" + PORT + '/');
        final Response response = target.path("greeting/en/Paul").request().get();

        assertThat(response.getStatus()).isEqualTo(200);
    }
}
