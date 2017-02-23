package com.mycompany.google.hashcode.exercises.pizza;

import com.mycompany.google.hashcode.exercises.Exercise;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class VideoStreaming extends Exercise {
    
    int amountOfVideos;
    int amountOfEndpoints;
    int amountOfRequestDescriptions;
    int amountOfCaches;
    int maxCacheSize;
    
    Map<Integer, Video> videos = new HashMap<>();
    Map<Integer, EndPoint> endpoints = new HashMap<>();
    Map<Integer, Cache> caches = new HashMap<>();
    
    public static void solve(Scanner input, BufferedWriter output) throws IOException {
        new VideoStreaming(input, output)
                .solve()
                .close();
    }

    private VideoStreaming(Scanner input, BufferedWriter output) {
        super(input, output);
    }
    
    @Override
    protected VideoStreaming solve() throws IOException {
        readBasicInfo();
        readVideos();
        for(int i = 0; i < amountOfEndpoints; i++) {
            readEndpoint(i);
        }
        for(int j = 0; j < amountOfRequestDescriptions; j++) {
            readRequestDescription();
        }
        return this;
    }

    private void readBasicInfo() {
        String[] line = input.next().split(" ");
        amountOfVideos = Integer.parseInt(line[0]);
        amountOfEndpoints = Integer.parseInt(line[1]);
        amountOfRequestDescriptions = Integer.parseInt(line[2]);
        amountOfCaches = Integer.parseInt(line[3]);
        maxCacheSize = Integer.parseInt(line[4]);
    }

    private void readVideos() {
        Counter counter = new Counter();
        String[] line = input.next().split(" ");
        asList(line).stream()
                .map(Integer::parseInt)
                .forEach(size -> {
                    videos.put(counter.value, new Video(size));
                    counter.increment();
                });
    }

    private void readEndpoint(int id) {
        String[] line = input.next().split(" ");
        EndPoint endPoint = new EndPoint();
        endPoint.datacenterLatency = Integer.parseInt(line[0]);
        int amountOfCacheConnections = Integer.parseInt(line[1]);
        for (int i = 0; i < amountOfCacheConnections; i++) {
            line = input.next().split(" ");
            int cacheId = Integer.parseInt(line[0]);
            int latency = Integer.parseInt(line[1]);
            endPoint.cacheLatencyMap.put(cacheId, latency);
            caches.put(cacheId, new Cache(maxCacheSize));
        }
        endpoints.put(id, endPoint);
    }

    private void readRequestDescription() {
        String[] line = input.next().split(" ");
        int vidId = Integer.parseInt(line[0]);
        int endpointId = Integer.parseInt(line[1]);
        int requestAmount = Integer.parseInt(line[2]);
        RequestDescription requestDescription = new RequestDescription(vidId, requestAmount);
        endpoints.get(endpointId).requestDescriptionList.add(requestDescription);
    }

    private static class Counter {
        public int value = 0;

        public void increment() {
            value++;
        }
    }
    
    
}
