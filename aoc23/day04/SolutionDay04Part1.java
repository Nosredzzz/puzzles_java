package aoc23.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDay04Part1 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day04/input.txt";

        int globalPointsSum = 0;

        List<List<Integer>> partNumbersMatrix = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(lineNumber + ": " + line);

                // Split the line by ":"
                String[] lineParts = line.split(":");
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
                if (currentCardWinningPoints > 0) {
                    globalPointsSum += (int) Math.pow(2, (currentCardWinningPoints-1));
                }
                System.out.println("***");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n***********************************");
        System.out.println("globalPointsSum: " + globalPointsSum);
        System.out.println("***********************************");
    }
}
