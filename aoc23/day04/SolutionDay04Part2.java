package aoc23.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionDay04Part2 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day04/input.txt";

        List<Integer> cardsToVisit = new ArrayList<>();
        Map<Integer,Integer> pointsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(lineNumber + ": " + line);

                // Split the line by ":"
                String[] lineParts = line.split(":");
                // Get the first part as the card number
                String cardNumberInfo = lineParts[0].replace("Card ", "").trim();
                int cardNumber = Integer.parseInt(cardNumberInfo);
                // Get the second part as the points cards
                String pointsCardsInfo = lineParts[1];
                // Split the pointsCardsInfo by "|"
                String[] pointsCardsInfoParts = pointsCardsInfo.split("\\|");
                // Get the first part as the winning points
                String winningPointsInfo = pointsCardsInfoParts[0];
                // Split the winningPointsInfo by " "
                String[] winningPoints = winningPointsInfo.split(" ");
                // Get the second part as the points of the current card
                String currentCardPointsInfo = pointsCardsInfoParts[1];
                // Split the currentCardPointsInfo by " "
                String[] currentCardPoints = currentCardPointsInfo.split(" ");
                // Convert the currentCardPoints to allow a O(1) search
                List<String> currentCardPointsList = new ArrayList<>();
                for (String currentCardPoint : currentCardPoints) {
                    currentCardPoint = currentCardPoint.trim();
                    if (!currentCardPoint.equals("")) {
                        currentCardPointsList.add(currentCardPoint);
                    }
                }
                //System.out.println("winningPoints: " + winningPointsInfo);
                //System.out.println("currentCardPoints: " + currentCardPointsInfo);


                // Calculate the number of winning points for the current card
                int currentCardWinningPoints = 0;
                for (String winningPoint : winningPoints) {
                    // test if the winningPoint is one of the currentCardPoints
                    if (currentCardPointsList.contains(winningPoint)) {
                        System.out.println("winningPoint: " + winningPoint);
                        currentCardWinningPoints++;
                    }
                }
                System.out.println("currentCardWinningPoints: " + currentCardWinningPoints);
                cardsToVisit.add(cardNumber);
                pointsMap.put(cardNumber, currentCardWinningPoints);
                System.out.println("***");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int i = 0;

        // saving the current time do check the execution time
        long startTime = System.currentTimeMillis();

        // doing the BFS to calculate the amount of cards that will be visited
        while (i<cardsToVisit.size()){
            int currentNode = cardsToVisit.get(i);
            int currentPoints = pointsMap.get(currentNode);
            System.out.println("currentNode: " + currentNode + " currentPoints: " + currentPoints);
            if (currentPoints > 0) {
                // add the children of the current node to the list
                for (int j = 0; j < currentPoints; j++) {
                    cardsToVisit.add(currentNode + j + 1);
                }
            }
            i++;
        }

        // saving the current time do check the execution time
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime); // in milliseconds

        System.out.println("\n***********************************");
        System.out.println("Total of Cards: " + cardsToVisit.size());
        System.out.println("Execution time: " + duration + " milliseconds");
        System.out.println("***********************************");
    }
}
