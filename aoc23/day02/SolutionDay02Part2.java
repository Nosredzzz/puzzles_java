package aoc23.day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SolutionDay02Part2 {
    public static void main(String[] args){
        String inputFilePath = "aoc23/day02/input.txt";

        int globalPowerSum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(lineNumber + ": " + line);

                String[] currentGameSplit = line.split(":");

                Map<String, Integer> maxCubesMap = new HashMap<>();
                maxCubesMap.put("red", 0);
                maxCubesMap.put("green", 0);
                maxCubesMap.put("blue", 0);

                String[] currentGameLogs = currentGameSplit[1].split(";");

                for (String gameLogValue : currentGameLogs) {
                    String[] gameLogValueSplit = gameLogValue.split(",");
                    //System.out.println("New round");
                    for (String gameLogValuePart : gameLogValueSplit) {
                        String[] gameLogValuePartSplit = gameLogValuePart.trim().split(" ");
                        int cubesNumber = Integer.parseInt(gameLogValuePartSplit[0]);
                        String cubeColor = gameLogValuePartSplit[1];
                        // check if the cubesNumber is greater than the maxCubeColor
                        if (cubesNumber > maxCubesMap.get(cubeColor)) {
                            System.out.println("*** Updating max cube color: " + cubeColor +
                                    " with value: " + cubesNumber);
                            maxCubesMap.put(cubeColor, cubesNumber);
                        }
                    }
                }
                int localPowerSum = maxCubesMap.get("red") * maxCubesMap.get("green") * maxCubesMap.get("blue");
                globalPowerSum += localPowerSum;
                System.out.println("localPowerSum: " + localPowerSum);
                System.out.println("globalIdsSum: " + globalPowerSum);
            }
            System.out.println("Final global sum: " + globalPowerSum);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
