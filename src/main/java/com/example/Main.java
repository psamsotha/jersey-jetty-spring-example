package com.example;

import com.example.resource.GreetingResource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author Paul Samsotha.
 */
public class Main {

    private static final Log LOG = LogFactory.getLog(Main.class);
    private static final int PORT = 9090;

    static Server getApplicationServer(int port) {
        final ResourceConfig config = new ResourceConfig()
                .packages("com.example.resource");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setErrorHandler(null);
        context.setContextPath("/");

        // For Spring. Along with adding the jersey-spring4 dependency,
        // adding a ContextLoaderListener with a WebApplicationContext
        // is enough to make the jersey/spring integration work.
        context.addEventListener(new ContextLoaderListener(getWac()));

        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        jerseyServlet.setInitOrder(0);
        context.addServlet(jerseyServlet, "/*");

        Server server = new Server(port);
        server.setHandler(context);

        return server;
    }

    private static WebApplicationContext getWac() {
        final AnnotationConfigWebApplicationContext wac = new AnnotationConfigWebApplicationContext();
        wac.register(SpringConfig.class);
        return wac;
    }

    public static void main(String... args) throws Exception {
        final Server server = getApplicationServer(PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (server.isStarted()) {
                server.setStopAtShutdown(true);
                try {
                    server.stop();
                } catch (Exception ex) {
                    LOG.error("Error shutting down server.", ex);
                }
            }
        }));

        server.start();
        LOG.info("Server started on port " + PORT);
        server.join();
    }
}
