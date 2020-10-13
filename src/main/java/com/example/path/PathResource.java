package com.example.path;

import com.example.path.model.PassengerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class PathResource {
    private final RoadService roadService;

    public PathResource(RoadService roadService) {
        this.roadService = roadService;
    }

    @GetMapping("/path/{from}/{to}/{time}")
    public ResponseEntity<String> getPath(@PathVariable String from, @PathVariable String to, @PathVariable String time) {
        return ResponseEntity.ok(roadService.getPathBetween(from, to, time));
    }

    @PostMapping("passenger")
    public ResponseEntity<String> calculatePassengerEstimation(@RequestBody PassengerRequest passengerRequest){
        return ResponseEntity.ok(roadService.getPathCost(passengerRequest));
    }
}
