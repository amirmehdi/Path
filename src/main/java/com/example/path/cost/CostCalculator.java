package com.example.path.cost;

import com.example.path.model.CostReport;
import com.example.path.model.RoadDetail;
import com.example.path.model.Transport;

import java.util.List;

public interface CostCalculator {
    CostReport getCostReport(Transport ref, List<Integer> passengers);
}
