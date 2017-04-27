package uk.co.example.greeting;

import com.fasterxml.jackson.annotation.JsonProperty;

class Greeting {
    @JsonProperty("greeting")
    private String greeting;

    Greeting(String greeting) {
        this.greeting = greeting;
    }
}
