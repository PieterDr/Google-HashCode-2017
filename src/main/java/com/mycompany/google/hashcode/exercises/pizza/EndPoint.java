package com.mycompany.google.hashcode.exercises.pizza;

import java.util.Map;

public class EndPoint {
    
     public int datacenterLatency;
     public Map<Integer, Integer> cacheLatencyMap;

    public EndPoint(int datacenterLatency, Map<Integer, Integer> cacheLatencyMap) {
        this.datacenterLatency = datacenterLatency;
        this.cacheLatencyMap = cacheLatencyMap;
    }
    
}
