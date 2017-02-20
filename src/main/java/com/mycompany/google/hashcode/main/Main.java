package com.mycompany.google.hashcode.main;

import com.mycompany.google.hashcode.exercises.pizza.Pizza;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws IOException {
        Pizza.solve(initScanner("pizza/small.in"), initOutputWriter("output/pizza.out"));
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
