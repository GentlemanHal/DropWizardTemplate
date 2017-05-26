package uk.co.example.serviceName;

import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

/* Without @PreMatching and @Priority the LoggingFilter is applied first and thus doesn't get the correlation id */
@Provider
@PreMatching
@Priority(MIN_VALUE)
public class CorrelationIdFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String correlationId = requestContext.getHeaderString("Correlation-Id");
        if (isBlank(correlationId)) {
            // Generate one so we can at least correlate the logs from myself
            correlationId = format("%016x", ThreadLocalRandom.current().nextLong());
        }
        MDC.put("correlationId", correlationId);
    }
}
