package uk.co.example.serviceName;

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
import uk.co.example.serviceName.admin.VersionServlet;
import uk.co.example.serviceName.api.HelloWorldResource;
import uk.co.example.serviceName.healthchecks.ExampleHealthCheck;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

// TODO: rename to the correct service name
public class ServiceName extends Application<ServiceNameConfiguration> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static ObjectMapper decorateObjectMapper(ObjectMapper objectMapper) {
        return objectMapper
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(final String[] args) throws Exception {
        new ServiceName().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceNameConfiguration> bootstrap) {
        decorateObjectMapper(bootstrap.getObjectMapper());
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    }

    @Override
    public void run(ServiceNameConfiguration configuration, Environment environment) throws Exception {
        logger.info("Initialising the image service...");

        environment.jersey().register(new HelloWorldResource());
        environment.healthChecks().register("example", new ExampleHealthCheck());
        environment.admin().addServlet("version", new VersionServlet()).addMapping("/version");
    }
}
