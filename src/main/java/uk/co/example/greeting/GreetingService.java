package uk.co.example.greeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class GreetingService extends Application<GreetingServiceConfiguration> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static ObjectMapper decorateObjectMapper(ObjectMapper objectMapper) {
        return objectMapper
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void initialize(Bootstrap<GreetingServiceConfiguration> bootstrap) {
        decorateObjectMapper(bootstrap.getObjectMapper());
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(GreetingServiceConfiguration configuration, Environment environment) throws Exception {
        logger.info("Initialising the image service...");

        environment.jersey().register(new HelloWorldResource());
        environment.healthChecks().register("example", new ExampleHealthCheck());
    }

    public static void main(final String[] args) throws Exception {
        new GreetingService().run(args);
    }
}
