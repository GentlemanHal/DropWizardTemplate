package uk.co.example.greeting.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Rule;
import org.junit.Test;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.example.greeting.GreetingService.decorateObjectMapper;

public class HelloWorldResourceTest {
    private HelloWorldResource resource = new HelloWorldResource();

    @Rule
    public final ResourceTestRule resources = ResourceTestRule.builder()
        .setMapper(decorateObjectMapper(new ObjectMapper()))
        .addResource(resource)
        .build();

    @Test
    public void shouldReturnTheTotalResultsFound() {
        String response = sayHello();
        String greeting = JsonPath.read(response, "greeting");
        assertThat(greeting).isEqualTo("Hello World!");
    }

    private String sayHello() {
        return resources.client()
            .target("/greeting")
            .request(APPLICATION_JSON_TYPE)
            .get(String.class);
    }
}