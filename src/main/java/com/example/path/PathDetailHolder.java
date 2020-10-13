package com.example.path;

import com.example.path.model.FlightDetail;
import com.example.path.model.RoadDetail;
import com.example.path.model.RoadDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PathDetailHolder {
    private final ObjectMapper objectMapper;

    private List<RoadDetail> roadDetails = new ArrayList<>();
    private List<FlightDetail> flightDetails = new ArrayList<>();
    public PathDetailHolder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void load() throws IOException {
        String str = loadRoadFile();

        RoadDetails dto = objectMapper.readValue(str, RoadDetails.class);
        roadDetails = dto.getRoadDetails();
        flightDetails = dto.getFlightDetails();
    }

    private String loadRoadFile() throws IOException {
        File file = new File("road.json");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        String str = new String(data, "UTF-8");
        return str;
    }

    public List<RoadDetail> getRoadDetails() {
        return roadDetails;
    }

    public List<FlightDetail> getFlightDetails() {
        return flightDetails;
    }
}
