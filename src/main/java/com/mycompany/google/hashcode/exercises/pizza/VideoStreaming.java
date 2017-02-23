package com.mycompany.google.hashcode.exercises.pizza;

import com.mycompany.google.hashcode.exercises.Exercise;
import java.io.BufferedWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.ws.Endpoint;

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
        new VideoStreaming(input, output).solve().close();
    }

    private VideoStreaming(Scanner input, BufferedWriter output) {
        super(input, output);
    }

    @Override
    protected VideoStreaming solve() throws IOException {
        readBasicInfo();

        readVideos();
        for (int i = 0; i < amountOfEndpoints; i++) {
            readEndpoint(i);
        }
        for (int j = 0; j < amountOfRequestDescriptions; j++) {
            readRequestDescription();
        }

        Map<Integer, Map<Integer, Integer>> scores = new HashMap<>();

        for (EndPoint endPoint : endpoints.values()) {
            for (RequestDescription desc : endPoint.requestDescriptionList) {
                endPoint.cacheLatencyMap.entrySet().forEach(entry -> {
                    Integer cacheId = entry.getKey();
                    Map<Integer, Integer> videoScores = scores.get(cacheId);
                    if (videoScores == null) {
                        videoScores = new HashMap<>();
                        scores.put(cacheId, videoScores);
                    }
                    Integer score = videoScores.get(desc.vidId);
                    if (score == null) {
                        score = 0;
                    }
                    videoScores.put(desc.vidId, score + nrOfMillisSaved(endPoint.datacenterLatency,
                            endPoint.cacheLatencyMap.get(cacheId), desc.requestAmount));
                });
            }
        }

        scores.entrySet().forEach(entry -> {
            Integer cacheId = entry.getKey();
            Cache cache = caches.get(cacheId);
            AtomicInteger cacheSize = new AtomicInteger(cache.maxSize);
            List<Integer> selected = new ArrayList<>();
            entry.getValue().entrySet().stream() //
                    .sorted((a, b) -> {
                        return b.getValue() - a.getValue();
                    }).forEach(videoEntry -> {
                        Integer vidId = videoEntry.getKey();
                        Video video = videos.get(vidId);
                        if (video.size <= cacheSize.get()) {
                            selected.add(vidId);
                            cacheSize.set(cacheSize.get() - video.size);
                        }
                    });
            System.out.println(cacheId + " " + selected);
        });
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

    private int nrOfMillisSaved(int dataCenterLatency, int cacheLatency, int nrOfRequests) {
        return (dataCenterLatency - cacheLatency) * nrOfRequests;
    }

    private void readVideos() {
        Counter counter = new Counter();
        String[] line = input.next().split(" ");
        asList(line).stream().map(Integer::parseInt).forEach(size -> {
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
