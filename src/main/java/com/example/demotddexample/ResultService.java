package com.example.demotddexample;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private final IResultRepository resultRepository;

    public ResultService(IResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public TrafficLight getTraffiLight(int year) {
        List<Integer> results = resultRepository.findByYear(year);

        if (results.contains(1)) {
            return TrafficLight.GREEN;
        } else if (results.contains(2)) {
            return TrafficLight.ORANGE;
        }

        return TrafficLight.RED;
    }
}
