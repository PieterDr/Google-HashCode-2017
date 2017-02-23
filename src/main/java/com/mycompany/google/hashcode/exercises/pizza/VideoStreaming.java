package com.mycompany.google.hashcode.exercises.pizza;

import com.mycompany.google.hashcode.exercises.Exercise;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    
    
}
