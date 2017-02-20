package com.mycompany.google.hashcode.exercises.pizza;

import com.mycompany.google.hashcode.exercises.Exercise;
import java.io.BufferedWriter;
import java.io.IOException;
import static java.util.Arrays.asList;
import java.util.Scanner;

public class Pizza extends Exercise {

    private int rows;
    private int columns;
    private int minAmountOfIngredients;
    private int maxNumberOfCells;
    private Ingredient[][] pizza;
    

    public static void solve(Scanner input, BufferedWriter output) throws IOException {
        new Pizza(input, output)
                .solve()
                .close();
    }

    private Pizza(Scanner input, BufferedWriter output) {
        super(input, output);
    }

    @Override
    protected Pizza solve() throws IOException {
        readMetaInfo();
        readPizza();
        printPizza();
        return this;
    }

    private void readMetaInfo() {
        String[] line = input.next().split(" ");
        rows = Integer.parseInt(line[0]);
        columns = Integer.parseInt(line[1]);
        minAmountOfIngredients = Integer.parseInt(line[2]);
        maxNumberOfCells = Integer.parseInt(line[3]);
        pizza = new Ingredient[rows][columns];
    }

    private void readPizza() {
        for(int i = 0; i < rows; i++) {
            readPizzaLine(i);
        }
    }

    private void readPizzaLine(int row) {
        String line = input.next();
        for(int i = 0; i < columns; i++) {
            pizza[row][i] = Ingredient.of(line.substring(i, i+1));
        }
    }

    //For testing purposes
    private void printPizza() {
        for (Ingredient[] row : pizza) {
            for (Ingredient ingredient : row) {
                System.out.print(ingredient);
            }
            System.out.println();
        }
    }
    
    private static enum Ingredient {
        M, T;
        
        public static Ingredient of(String ingredient) {
            return asList(values()).stream()
                    .filter(value -> value.name().equalsIgnoreCase(ingredient))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Impossible"));
        }
    }

}
