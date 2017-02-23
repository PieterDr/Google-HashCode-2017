package com.mycompany.google.hashcode.main;

import com.mycompany.google.hashcode.exercises.pizza.VideoStreaming;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws IOException {
//        Pizza.solve(initScanner("pizza/small.in"), initOutputWriter("output/pizza.out"));
        long before = System.currentTimeMillis();
        VideoStreaming.solve(initScanner("videos_worth_spreading.in"), initOutputWriter("output/videos_worth_spreading.out"));
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }

    private static Scanner initScanner(String resource) {
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream(resource));
        scanner.useDelimiter("\r\n");
        return scanner;
    }

    private static BufferedWriter initOutputWriter(String output) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(output))));
    }
    
}
