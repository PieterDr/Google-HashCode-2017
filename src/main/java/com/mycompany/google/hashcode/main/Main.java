package com.mycompany.google.hashcode.main;

import com.mycompany.google.hashcode.exercises.videostreaming.VideoStreaming;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // Pizza.solve(initScanner("pizza/small.in"),
        // initOutputWriter("output/pizza.out"));
        long before = System.currentTimeMillis();
        Arrays.asList(/*"videos_worth_spreading", */"kittens", "me_at_the_zoo", "trending_today").forEach(file -> {
            try {
                VideoStreaming.solve(initScanner(file + ".in"), initOutputWriter("output/" + file + ".out"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }

    private static Scanner initScanner(String resource) {
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream(resource));
        scanner.useDelimiter("\n");
        return scanner;
    }

    private static BufferedWriter initOutputWriter(String output) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(output))));
    }

}
