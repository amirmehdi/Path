package com.example.path.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PassengerRequest {


    @JsonProperty("source")
    public String source;
    @JsonProperty("destination")
    public String destination;
    @JsonProperty("departure_time")
    public String departureTime;
    @JsonProperty("passengers")
    public List<Integer> passengers = null;
}
