package uk.co.example.greeting.api;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("greeting")
public class HelloWorldResource {
    @GET
    @Timed
    @Produces(APPLICATION_JSON)
    public Greeting sayHello() {
        return new Greeting("Hello World!");
    }
}
