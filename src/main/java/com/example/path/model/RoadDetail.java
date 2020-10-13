package com.example.path.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class RoadDetail extends Transport {
    @JsonProperty("length")
    public int length;
    @JsonProperty("speed_limit")
    public int speedLimit;

    public RoadDetail() {
        super("Road");
    }

    @JsonProperty("road_name")
    public String getName() {
        return name;
    }

    @JsonProperty("road_name")
    @Override
    public void setName(String roadName) {
        this.name = roadName;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    @JsonIgnore
    public int getDuration() {
        return (int) (60.0 * getLength() / getSpeedLimit());
    }
}
