package aoc23.day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SolutionDay02Part1 {
    public static void main(String[] args){
        String inputFilePath = "aoc23/day02/input.txt";

        int globalIdsSum = 0;

        int maxCubeRed = 12;
        int maxCubeGreen = 13;
        int maxCubeBlue = 14;

        Map<String, Integer> maxCubesMap = new HashMap<>();
        maxCubesMap.put("red", maxCubeRed);
        maxCubesMap.put("green", maxCubeGreen);
        maxCubesMap.put("blue", maxCubeBlue);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(lineNumber + ": " + line);

                String[] currentGameSplit = line.split(":");
                int currentGameId = Integer.parseInt(currentGameSplit[0].replace("Game ", ""));
                //System.out.println("currentGameId: " + currentGameId);

                String[] currentGameLogs = currentGameSplit[1].split(";");
                boolean gameIsImpossible = false;
                for (String gameLogValue : currentGameLogs) {
                    if (gameIsImpossible) {
                        break;
                    }
                    String[] gameLogValueSplit = gameLogValue.split(",");
                    //System.out.println("New round");
                    for (String gameLogValuePart : gameLogValueSplit) {
                        String[] gameLogValuePartSplit = gameLogValuePart.trim().split(" ");
                        int cubesNumber = Integer.parseInt(gameLogValuePartSplit[0]);
                        String cubeColor = gameLogValuePartSplit[1];
                        // check if the cubesNumber is greater than the maxCubeColor
                        if (cubesNumber > maxCubesMap.get(cubeColor)) {
                            System.out.println("*** This game is impossible");
                            // if yes, then it's a impossible game
                            // set the gameIsImpossible to true and break the loop
                            gameIsImpossible = true;
                            break;
                        }
                    }
                }
                if (!gameIsImpossible) {
                    globalIdsSum += currentGameId;
                }
                System.out.println("globalIdsSum: " + globalIdsSum);

            }
            System.out.println("Final global sum: " + globalIdsSum);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
