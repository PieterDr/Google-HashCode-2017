package com.mycompany.google.hashcode.exercises;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Exercise {
    
    protected Scanner input;
    protected BufferedWriter output;

    protected Exercise(Scanner input, BufferedWriter output) {
        this.input = input;
        this.output = output;
    }

    protected void close() throws IOException {
        input.close();
        output.close();
    }
    
    protected abstract Exercise solve() throws IOException;
}
