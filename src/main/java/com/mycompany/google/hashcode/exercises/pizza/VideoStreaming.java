package com.mycompany.google.hashcode.exercises.pizza;

import com.mycompany.google.hashcode.exercises.Exercise;
import java.io.BufferedWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.ws.Endpoint;

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

        Map<Integer, Map<Integer, Integer>> scores = new HashMap<>();

        for (RequestDescription desc : Arrays.asList(new RequestDescription(27, 4, 370))) {
            EndPoint endPoint = endpoints.get(desc.endpointId);
            endPoint.cacheLatencyMap.entrySet().forEach(entry -> {
                Integer cacheId = entry.getKey();
                Cache cache = caches.get(cacheId);
                Map<Integer, Integer> videoScores = scores.get(entry.getValue());
                if (videoScores == null) {
                    videoScores = new HashMap<>();
                    scores.put(entry.getValue(), videoScores);
                }
                Integer score = videoScores.get(desc.vidId);
                if (score == null) {
                    score = 0;
                }
                videoScores.put(desc.vidId, score + nrOfMillisSaved(endPoint.datacenterLatency,
                        endPoint.cacheLatencyMap.get(cacheId), desc.requestAmount));
            });

        }

        scores.entrySet().forEach(entry -> {
            Integer cacheId = entry.getKey();
            Cache cache = caches.get(cacheId);
            AtomicInteger cacheSize = new AtomicInteger(cache.maxSize);
            entry.getValue().entrySet().stream() //
                    .sorted((a, b) -> {
                        return b.getValue() - a.getValue();
                    }).forEach(videoEntry -> {
                        Integer vidId = videoEntry.getKey();
                        Video video = videos.get(vidId);
                        if (video.size <= cacheSize.get()) {
                            System.out.println(cacheId + " " + vidId);
                            cacheSize.addAndGet(-video.size);
                        }
                    });
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

}
