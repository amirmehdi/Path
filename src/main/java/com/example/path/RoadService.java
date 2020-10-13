package com.example.path;

import com.example.path.cost.CostCalculator;
import com.example.path.cost.RoadCostCalculationService;
import com.example.path.model.CostReport;
import com.example.path.model.PassengerRequest;
import com.example.path.model.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoadService {
    private final ShortestPathService shortestPathService;
    private final PathSelectorService pathSelectorService;

    private Map<String, CostCalculator> costCalculators = new HashMap<>();


    public RoadService(ShortestPathService shortestPathService, PathSelectorService pathSelectorService) {
        this.shortestPathService = shortestPathService;
        this.pathSelectorService = pathSelectorService;
    }

    @Autowired
    public void setCostCalculators(List<CostCalculator> costCalculators) {
        for (CostCalculator costCalculator : costCalculators) {
            String simpleName = costCalculator.getClass().getSimpleName();
            this.costCalculators.put(simpleName.substring(0, simpleName.indexOf('C')), costCalculator);
        }
    }


    public String getPathBetween(String from, String to, String time) {
        List<String> vectorPath = shortestPathService.printShortestDistance(from, to);
        LocalTime start = LocalTime.parse(time);
        int duration = 0;

        Message message = new Message().addTitle("result for your query is:")
                .addSplitter();
        for (int i = 0; i < vectorPath.size() - 1; i++) {
            String source = vectorPath.get(i);
            String dest = vectorPath.get(i + 1);

            Transport ref = pathSelectorService.getBest(source, dest, start.plusMinutes(duration));

            message.addRefInfo(start, duration, ref).addSplitter();
            duration += ref.getDuration();
        }
        return message.addTotalDuration(duration).toString();
    }

    public String getPathCost(PassengerRequest passengerRequest) {
        List<String> vectorPath = shortestPathService.printShortestDistance(passengerRequest.getSource(), passengerRequest.getDestination());

        Message message = new Message().addTitle("result for your query is:")
                .addSplitter();
        LocalTime start = LocalTime.parse(passengerRequest.getDepartureTime());
        int duration = 0, totalCost = 0;
        for (int i = 0; i < vectorPath.size() - 1; i++) {
            String source = vectorPath.get(i);
            String dest = vectorPath.get(i + 1);

            Transport ref = pathSelectorService.getBest(source, dest, start.plusMinutes(duration));
            CostReport costReport = costCalculators.get(ref.getType()).getCostReport(ref, passengerRequest.getPassengers());
            message.addRefInfo(start, duration, ref)
                    .addPricing(costReport)
                    .addSplitter();
            duration += ref.getDuration();
            totalCost += costReport.getCost();
        }
        message.addTotalDuration(duration).addTotalCost(totalCost);
        return message.toString();
    }
}
