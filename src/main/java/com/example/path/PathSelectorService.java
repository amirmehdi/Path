package com.example.path;

import com.example.path.model.FlightDetail;
import com.example.path.model.RoadDetail;
import com.example.path.model.Transport;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class PathSelectorService {

    public static final int TIME_TO_TAKE_OFF = 30;
    private final PathDetailHolder pathDetailHolder;

    public PathSelectorService(PathDetailHolder pathDetailHolder) {
        this.pathDetailHolder = pathDetailHolder;
    }

    public Transport getBest(String source, String dest, LocalTime time){
        Optional<FlightDetail> flightDetail = pathDetailHolder.getFlightDetails().stream().filter(roadDetail -> roadDetail.getSource().equals(source) ||
                roadDetail.getDest().equals(dest)).findFirst();
        if (flightDetail.isPresent()){
            if (time.plusMinutes(TIME_TO_TAKE_OFF).isBefore(LocalTime.parse(flightDetail.get().getTakeOffTime()))){
                return flightDetail.get();
            }
        }
        return pathDetailHolder.getRoadDetails().stream().filter(roadDetail -> roadDetail.getSource().equals(source) ||
                roadDetail.getDest().equals(dest)).findFirst().orElseThrow(RuntimeException::new);

    }
}
