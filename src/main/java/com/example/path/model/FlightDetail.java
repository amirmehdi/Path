package com.example.path.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class FlightDetail extends Transport {

    @JsonProperty("price_per_person")
    public int pricePerPerson;
    @JsonProperty("capacity")
    public int capacity;
    @JsonProperty("take_off_time")
    public String takeOffTime;
    @JsonProperty("landing_time")
    public String landingTime;

    public FlightDetail() {
        super( "Flight");
    }

    @JsonProperty("flight_name")
    public String getName() {
        return name;
    }

    @JsonProperty("flight_name")
    public void setName(String flightName) {
        this.name = flightName;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("destination")
    public String getDest() {
        return dest;
    }

    @JsonProperty("destination")
    public void setDest(String destination) {
        this.dest = destination;
    }

    @Override
    public int getDuration() {
        return (int) ChronoUnit.MINUTES.between(LocalTime.parse(landingTime),LocalTime.parse(takeOffTime));
    }

    @JsonProperty("take_off_time")
    public String getTakeOffTime() {
        return takeOffTime;
    }

    @JsonProperty("take_off_time")
    public void setTakeOffTime(String takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    @JsonProperty("landing_time")
    public String getLandingTime() {
        return landingTime;
    }

    @JsonProperty("landing_time")
    public void setLandingTime(String landingTime) {
        this.landingTime = landingTime;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
