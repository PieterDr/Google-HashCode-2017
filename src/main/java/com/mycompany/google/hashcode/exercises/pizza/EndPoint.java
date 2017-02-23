package com.mycompany.google.hashcode.exercises.pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndPoint {

    public int datacenterLatency;
    public Map<Integer, Integer> cacheLatencyMap = new HashMap<>();
    public List<RequestDescription> requestDescriptionList = new ArrayList<>();

    public EndPoint() {
    }

    public EndPoint(int datacenterLatency, Map<Integer, Integer> cacheLatencyMap,
            List<RequestDescription> requestDescriptionList) {
        this.datacenterLatency = datacenterLatency;
        this.cacheLatencyMap = cacheLatencyMap;
        this.requestDescriptionList = requestDescriptionList;
    }
}
