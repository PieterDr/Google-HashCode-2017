package com.mycompany.google.hashcode.exercises.pizza;

import com.mycompany.google.hashcode.exercises.Exercise;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class VideoStreaming extends Exercise {

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
        // TODO implement
        return this;
    }
    
    
}
