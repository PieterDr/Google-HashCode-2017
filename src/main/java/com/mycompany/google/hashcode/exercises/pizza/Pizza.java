package com.mycompany.google.hashcode.exercises.pizza;

import com.mycompany.google.hashcode.exercises.Exercise;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Pizza extends Exercise {
    
    public static void solve(Scanner input, BufferedWriter output) throws IOException {
        new Pizza(input, output)
                .solve()
                .close();
    }

    private Pizza(Scanner input, BufferedWriter output) {
        super(input, output);
    }
    
    protected Pizza solve() throws IOException {
        while(input.hasNext()) {
            output.write(input.next());
            output.newLine();
        }
        //TODO implement solution
        return this;
    }
    
}