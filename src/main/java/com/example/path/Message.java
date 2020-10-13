package com.example.path;

import com.example.path.model.CostReport;
import com.example.path.model.FlightDetail;
import com.example.path.model.RoadDetail;
import com.example.path.model.Transport;

import java.time.LocalTime;

public class Message {
    private StringBuilder stringBuilder = new StringBuilder();
    private String splitter = "\n**************************************************";

    public Message addTitle(String title) {
        stringBuilder.append("\n").append(title);
        return this;
    }


    public Message addSplitter() {
        stringBuilder.append(splitter);
        return this;
    }

    public Message addType(String type) {
        stringBuilder.append("\ntype:").append(type);
        return this;
    }

    public Message addRoadName(String roadName) {
        stringBuilder.append("\nreference: ").append(roadName);
        return this;
    }

    public Message addRoute(Transport ref, LocalTime start) {
        LocalTime time = ref instanceof RoadDetail ? start.plusMinutes(((RoadDetail) ref).getDuration()) : LocalTime.parse(((FlightDetail)ref).getLandingTime());
        stringBuilder.append(String.format("\nroute: RASHT(10:55) --> RAMSAR(13:25)"
                , ref.getSource(), start, ref.getDest(),time ));
        return this;
    }

    public Message addDuration(Transport ref) {
        stringBuilder.append(String.format("\nduration: %s minutes", ref.getDuration()));
        return this;
    }

    public Message addPricing(CostReport costReport) {
        stringBuilder.append(String.format("\nprice: children(%s) adults(%s)\n" +
                "        %s car(s) required\t\n" +
                "        total:  %s", costReport.getChild(), costReport.getAdult(), costReport.getCarNeeded(), costReport.getCost()));
        return this;
    }

    public Message addTotalDuration(int duration) {

        stringBuilder.append("\nTotal duration: ")
                .append(duration).append(" minutes");
        return this;
    }

    public Message addTotalCost(int totalCost) {
        stringBuilder.append("\nTotal price: ").append(totalCost);

        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public Message addRefInfo(LocalTime start, int duration, Transport ref) {
        return addType(ref.getType())
                .addRoadName(ref.getName())
                .addRoute(ref, start.plusMinutes(duration))
                .addDuration(ref);
    }
}
