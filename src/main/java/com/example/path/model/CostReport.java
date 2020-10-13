package com.example.path.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CostReport {
    private int adult;
    private int child;
    private int infant;
    private int carNeeded;
    private int cost;
}
