package com.example.path.cost;

import com.example.path.model.CostReport;
import com.example.path.model.RoadDetail;
import com.example.path.model.Transport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("Road")
public class RoadCostCalculationService implements CostCalculator {

    public static final int CAPACITY = 4;
    public static final int AGE_THRESHOLD = 2;
    public static final int START_COST = 400000;
    public static final int PER_KILO_METER_COST = 6000;
    public static int LENGTH_THRESHOLD = 50;

    @Override
    public CostReport getCostReport(Transport transport, List<Integer> passengers) {
        RoadDetail ref = (RoadDetail) transport;
        int adult = passengers.stream().filter(integer -> integer > AGE_THRESHOLD).collect(Collectors.toList()).size();
        int carNeeded = (int) Math.ceil(1.0 * adult / CAPACITY);
        int cost = START_COST * carNeeded;
        if (ref.getLength() > LENGTH_THRESHOLD) {
            cost += (ref.getLength() - LENGTH_THRESHOLD) * PER_KILO_METER_COST * carNeeded;
        }
        return CostReport.builder()
                .adult(adult)
                .child(passengers.size() - adult)
                .cost(cost)
                .carNeeded(carNeeded).build();
    }

}
