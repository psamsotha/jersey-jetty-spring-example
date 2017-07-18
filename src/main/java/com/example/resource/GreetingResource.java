package com.example.resource;

import com.example.service.EnglishGreetingService;
import com.example.service.SpanishGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author Paul Samsotha.
 */
@Component
@Path("greeting")
@Produces("text/plain")
public class GreetingResource {

    private final EnglishGreetingService engService;
    private final SpanishGreetingService espService;


    @Autowired
    public GreetingResource(EnglishGreetingService engService,
                            SpanishGreetingService espService) {
        this.engService = engService;
        this.espService = espService;
    }


    @GET
    @Path("/{lang: es|en}/{name}")
    public String getEnGreeting(@PathParam("lang") String lang, @PathParam("name") String name) {
        if ("es".equals(lang)) {
            return this.espService.getGreeting(name);
        }
        return this.engService.getGreeting(name);
    }
}
