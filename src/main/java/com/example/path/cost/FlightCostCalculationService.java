package com.example.path.cost;

import com.example.path.model.CostReport;
import com.example.path.model.FlightDetail;
import com.example.path.model.Transport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Flight")
public class FlightCostCalculationService implements CostCalculator {

    public static final int ADULT_AGE_CAPACITY = 12;
    public static final int CHILD_AGE_THRESHOLD = 2;

    @Override
    public CostReport getCostReport(Transport transport, List<Integer> passengers) {
        FlightDetail ref = (FlightDetail) transport;
        int adult = (int) passengers.stream().filter(integer -> integer >= ADULT_AGE_CAPACITY).count();
        int child = (int) passengers.stream().filter(integer -> integer < ADULT_AGE_CAPACITY && integer > CHILD_AGE_THRESHOLD).count();
        int infant = passengers.size() - child - adult;

        int cost = adult * ref.getPricePerPerson();
        cost += child * 0.5 * ref.getPricePerPerson();
        cost += infant * 0.1 * ref.getPricePerPerson();

        return CostReport.builder()
                .adult(adult)
                .child(child)
                .infant(infant)
                .carNeeded((int) Math.ceil(1.0 * passengers.size() / ref.getCapacity()))
                .cost(cost).build();
    }

}
