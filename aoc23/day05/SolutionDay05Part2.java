package aoc23.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDay05Part2 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day05/input.txt";

        List<Long> seedsList = new ArrayList<>();
        List<List<List<Long>>> almanac = new ArrayList<>();
        List<List<Long>> almanacEntry = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            long lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().equals("")) {
                    // Skip the empty lines
                    continue;
                }

                //System.out.println(lineNumber + ": " + line);

                if (lineNumber == 1) {
                    // Get the first line as the seeds list
                    String[] seeds = line.replace("seeds: ", "").split(" ");
                    // convert the seeds to a list of Longs
                    for (String seed : seeds) {
                        seedsList.add(Long.parseLong(seed));
                    }
                    // skip the rest of the loop
                    continue;
                }

                // check if the line ends with "map:"
                if (line.endsWith("map:")) {
                    // create a new almanac entry
                    almanacEntry = new ArrayList<>();
                    almanac.add(almanacEntry);
                } else {
                    //split the line by spaces
                    String[] lineParts = line.split(" ");
                    ArrayList<Long> almanacEntryLine = new ArrayList<>();
                    // convert the parts to Longs
                    for (String linePart : lineParts) {
                        almanacEntryLine.add(Long.parseLong(linePart));
                    }
                    // add the line to the almanac entry
                    almanacEntry.add(almanacEntryLine);
                }


            }
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        }

        // control variable to keep track of the minimum location
        long minLocation = Long.MAX_VALUE;
        //System.out.println("***********************************\n");
        // iterate over the seeds
        int i = 0;
        while (i < seedsList.size()){
           long base_seed = seedsList.get(i);
           long end_seed = base_seed + seedsList.get(i+1);
           System.out.println("base_seed: " + base_seed + ", end_seed: " + end_seed);
           for (long j = base_seed; j < end_seed + 1; j++) {
               // set the source to the seed
               long source = j;
               //System.out.println("\n*****\nseed: " + j);
               // iterate over the almanac
               for (List<List<Long>> almanacSourceDestinationMap: almanac) {
                   // iterate over the almanac entry
                   //System.out.println("\n***\nChecking the maps for the source: " + source);
                   // control variable to check if the source is found
                   boolean sourceFound = false;
                   for (List<Long> almanacEntryLine : almanacSourceDestinationMap) {
                       // check if the source is in the almanac entry line
                       long sourceIndex = almanacEntryLine.get(1);
                       long destinationIndex = almanacEntryLine.get(0);
                       long range = almanacEntryLine.get(2);
                       //System.out.println("sourceIndex: " + sourceIndex + ", destinationIndex: " + destinationIndex + ", range: " + range);
                       if (sourceIndex <= source && source <= sourceIndex + range) {
                           // update the source
                           source = destinationIndex + (source - sourceIndex);
                           //System.out.println("Destination found: " + source);
                           // set the source found flag
                           sourceFound = true;
                           break;
                       }
                   }

                   if (!sourceFound) {
                       //System.out.println("Source not found: " + source);
                       // keep the source as is
                   }

               }
               // update the min location
               if (source < minLocation) {
                   minLocation = source;
               }
               //System.out.println("***********************************");
           }
           i += 2;
        }

        System.out.println("\n***********************************");
        System.out.println("minLocation: " + minLocation);
        System.out.println("***********************************");
    }
}
