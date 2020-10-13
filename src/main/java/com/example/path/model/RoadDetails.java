package com.example.path.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoadDetails {

    @JsonProperty("road_details")
    public List<RoadDetail> roadDetails = null;

    @JsonProperty("flight_details")
    public List<FlightDetail> flightDetails = null;
}
