package uk.co.example.greeting.healthchecks;

import com.codahale.metrics.health.HealthCheck;

import static com.codahale.metrics.health.HealthCheck.Result.healthy;

public class ExampleHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return healthy();
    }
}
