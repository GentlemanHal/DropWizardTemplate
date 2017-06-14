package uk.co.example.serviceName.api;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static uk.co.example.serviceName.api.MediaType.APPLICATION_JSON_UTF8;

@Path("hello")
public class HelloWorldResource {
    @GET
    @Timed
    @Produces(APPLICATION_JSON_UTF8)
    public Greeting sayHello() {
        return new Greeting("Hello World!");
    }
}
