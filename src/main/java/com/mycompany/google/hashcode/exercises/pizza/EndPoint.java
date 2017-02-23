package com.mycompany.google.hashcode.exercises.pizza;

import java.util.List;
import java.util.Map;

public class EndPoint {
    
     public int datacenterLatency;
     public Map<Integer, Integer> cacheLatencyMap;
    public List<RequestDescription> requestDescriptionList;

    public EndPoint() {
    }

    public EndPoint(int datacenterLatency, Map<Integer, Integer> cacheLatencyMap, List<RequestDescription> requestDescriptionList) {
        this.datacenterLatency = datacenterLatency;
        this.cacheLatencyMap = cacheLatencyMap;
        this.requestDescriptionList = requestDescriptionList;
    }
}
