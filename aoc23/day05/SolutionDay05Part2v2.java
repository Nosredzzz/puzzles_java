package aoc23.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MapEntry {
    long destinationStart;
    long sourceStart;
    long range;

    public MapEntry(long destinationStart, long sourceStart, long range) {
        this.destinationStart = destinationStart;
        this.sourceStart = sourceStart;
        this.range = range;
    }
}

public class SolutionDay05Part2v2 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day05/input.txt";
        List<MapEntry[]> almanac = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            long lineNumber = 0;
            List<MapEntry> almanacEntry = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                if (lineNumber == 1) {
                    continue; // Seeds are processed later
                }

                if (line.endsWith("map:")) {
                    if (!almanacEntry.isEmpty()) {
                        almanac.add(almanacEntry.toArray(new MapEntry[0]));
                        almanacEntry.clear();
                    }
                } else {
                    String[] parts = line.split(" ");
                    almanacEntry.add(new MapEntry(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2])));
                }
            }
            if (!almanacEntry.isEmpty()) {
                almanac.add(almanacEntry.toArray(new MapEntry[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long minLocation = Long.MAX_VALUE;

        // Assume the first line is already read and contains seed information.
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String[] seeds = reader.readLine().replace("seeds: ", "").split(" ");
            for (int i = 0; i < seeds.length; i += 2) {
                long baseSeed = Long.parseLong(seeds[i]);
                long range = Long.parseLong(seeds[i + 1]);
                System.out.println("Processing seeds: " + baseSeed + " to " + (baseSeed + range));
                for (long seed = baseSeed; seed < baseSeed + range; seed++) {
                    long location = processSeed(seed, almanac);
                    minLocation = Math.min(minLocation, location);
                }
                System.out.println("Current Minimum Location: " + minLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Final Minimum Location: " + minLocation);
    }

    private static long processSeed(long seed, List<MapEntry[]> almanac) {
        List<Long> sourceList = new ArrayList<>();
        sourceList.add(seed);
        for (MapEntry[] map : almanac) {
            List<Long> destinationList = new ArrayList<>();
            for (MapEntry entry : map) {
                for (long source : sourceList.stream().toList()) {
                    if (source >= entry.sourceStart && source <= entry.sourceStart + entry.range) {
                        destinationList.add(entry.destinationStart + (source - entry.sourceStart));
                        sourceList.remove(source);
                    }
                }
            }
            sourceList.addAll(destinationList);
        }
        return sourceList.stream().min(Long::compareTo).orElseThrow();
    }
}
