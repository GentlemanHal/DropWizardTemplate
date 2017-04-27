package uk.co.example.greeting.api;

import com.fasterxml.jackson.annotation.JsonProperty;

class Greeting {
    @JsonProperty("greeting")
    private String greeting;

    Greeting(String greeting) {
        this.greeting = greeting;
    }
}
