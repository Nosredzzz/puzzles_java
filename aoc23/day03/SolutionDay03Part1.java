package aoc23.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDay03Part1 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day03/input.txt";

        int globalPartNumbersSum = 0;

        List<List<Integer>> partNumbersMatrix = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(lineNumber + ": " + line);
                // iterate over each character in the line
                List<Integer> currentLinePartNumbers = new ArrayList<>();
                // iterate over each character in the line in reverse order to calculate the part numbers
                int base10Multiplier = 1;
                for (int i = line.length() - 1; i >= 0; i--) {
                    char currentChar = line.charAt(i);

                    if (currentChar == '.') {
                        // considering '.' as -2
                        currentLinePartNumbers.add(-2);
                        base10Multiplier = 1;
                    }
                    // test if is numeric
                    else if (Character.isDigit(currentChar)) {
                        // considering the currentChar as a number
                        int currentCharNumber = Character.getNumericValue(currentChar);
                        int currentPartNumber = currentCharNumber * base10Multiplier;
                        currentLinePartNumbers.add(currentPartNumber);
                        System.out.println("currentPartNumber: " + currentPartNumber +
                                " base10Multiplier: " + base10Multiplier);
                        base10Multiplier *= 10;
                    }
                    // anything else is a special character and we should consider it as -1
                    else {
                        currentLinePartNumbers.add(-1);
                        base10Multiplier = 1;
                    }
                }
                partNumbersMatrix.add(currentLinePartNumbers);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // iterate over the matrix to find the part numbers
        for (int i = 0; i < partNumbersMatrix.size(); i++) {
            boolean foundSymbol = false;
            int partialSum = 0;
            for (int j = 0; j < partNumbersMatrix.get(i).size(); j++) {
                int currentPartNumber = partNumbersMatrix.get(i).get(j);
                System.out.print(currentPartNumber + " ");
                if (currentPartNumber >= 0) {
                    // update the partial sum
                    partialSum += currentPartNumber;
                    // check if there is some -1 (former symbol) around the current position
                    if (!foundSymbol){
                        // check the 8 positions around the current position
                        if (i - 1 >= 0) {
                            if (j - 1 >= 0) {
                                // check the top left position
                                if (partNumbersMatrix.get(i - 1).get(j - 1) == -1) {
                                    foundSymbol = true;
                                    continue;
                                }
                            }
                            // check the top position
                            if (partNumbersMatrix.get(i - 1).get(j) == -1) {
                                foundSymbol = true;
                                continue;
                            }
                            // check the top right position
                            if (j + 1 < partNumbersMatrix.get(i).size()) {
                                if (partNumbersMatrix.get(i - 1).get(j + 1) == -1) {
                                    foundSymbol = true;
                                    continue;
                                }
                            }

                        }
                        if (j - 1 >= 0) {
                            // check the left position
                            if (partNumbersMatrix.get(i).get(j - 1) == -1) {
                                foundSymbol = true;
                                continue;
                            }
                        }
                        if (j + 1 < partNumbersMatrix.get(i).size()) {
                            // check the right position
                            if (partNumbersMatrix.get(i).get(j + 1) == -1) {
                                foundSymbol = true;
                                continue;
                            }
                        }
                        if (i + 1 < partNumbersMatrix.size()) {
                            if (j - 1 >= 0) {
                                // check the bottom left position
                                if (partNumbersMatrix.get(i + 1).get(j - 1) == -1) {
                                    foundSymbol = true;
                                    continue;
                                }
                            }
                            // check the bottom position
                            if (partNumbersMatrix.get(i + 1).get(j) == -1) {
                                foundSymbol = true;
                                continue;
                            }
                            // check the bottom right position
                            if (j + 1 < partNumbersMatrix.get(i).size()) {
                                if (partNumbersMatrix.get(i + 1).get(j + 1) == -1) {
                                    foundSymbol = true;
                                    continue;
                                }
                            }
                        }
                    }
                }
                // means that the currentPartNumber is -1 or -2
                else {
                    // the previous part number was finished
                    if (foundSymbol) {
                        System.out.print("+");
                        globalPartNumbersSum += partialSum;
                    }
                    // reset the control variables
                    foundSymbol = false;
                    partialSum = 0;
                }
            }
            // check if the last part number was finished
            if (foundSymbol) {
                System.out.print("+");
                globalPartNumbersSum += partialSum;
            }
            System.out.println();
        }
        System.out.println("Final global sum: " + globalPartNumbersSum);
    }

//    public static void dfs(int[][] matrix, int i, int j, boolean[][] visited) {
//        // check if the current position is valid
//        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
//            return;
//        }
//
//        // check if the current position is visited
//        if (visited[i][j]) {
//            return;
//        }
//
//        // check if the current position is a point (not a part number)
//        if (matrix[i][j] == -2) {
//            return;
//        }
//
//        // mark the current position as visited
//        visited[i][j] = true;
//
//        // check if the current position is a part number
//        if (matrix[i][j] >= 0) {
}
