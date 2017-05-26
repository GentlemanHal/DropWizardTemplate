package uk.co.example.serviceName.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Rule;
import org.junit.Test;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.example.serviceName.ServiceName.decorateObjectMapper;

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
            .target("/hello")
            .request(APPLICATION_JSON_TYPE)
            .get(String.class);
    }
}
