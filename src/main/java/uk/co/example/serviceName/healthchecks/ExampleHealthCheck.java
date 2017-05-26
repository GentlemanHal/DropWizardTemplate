package uk.co.example.serviceName.healthchecks;

import com.codahale.metrics.health.HealthCheck;

import static com.codahale.metrics.health.HealthCheck.Result.healthy;

// TODO: replace with a real health check
public class ExampleHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return healthy();
    }
}
