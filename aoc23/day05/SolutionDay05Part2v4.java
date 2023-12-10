package aoc23.day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

class MapEntry4 implements Comparable<MapEntry4> {
    long destinationStart;
    long sourceStart;
    long range;

    public MapEntry4(long destinationStart, long sourceStart, long range) {
        this.destinationStart = destinationStart;
        this.sourceStart = sourceStart;
        this.range = range;
    }

    // override the compareTo method
    @Override
    public int compareTo(MapEntry4 other) {
        return Long.compare(this.sourceStart, other.sourceStart);
    }
}
public class SolutionDay05Part2v4 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day05/input.txt";
        List<PriorityQueue<MapEntry4>> almanac = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            long lineNumber = 0;
            PriorityQueue<MapEntry4> almanacEntry = null;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                if (lineNumber == 1) {
                    continue; // Seeds are processed later
                }

                if (line.endsWith("map:")) {
                    almanacEntry = new PriorityQueue<>();
                    almanac.add(almanacEntry);
                } else {
                    String[] parts = line.split(" ");
                    almanacEntry.add(new MapEntry4(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2])));
                }
            }
            if (!almanacEntry.isEmpty()) {
                almanac.add(almanacEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //long minLocation = Long.MAX_VALUE;

        AtomicLong minLocation = new AtomicLong(Long.MAX_VALUE);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()-3);

        // Assume the first line is already read and contains seed information.
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String[] seeds = reader.readLine().replace("seeds: ", "").split(" ");
            for (int i = 0; i < seeds.length; i += 2) {
                long baseSeed = Long.parseLong(seeds[i]);
                long range = Long.parseLong(seeds[i + 1]);
                long endSeed = baseSeed + range - 1;  // inclusive
                System.out.println("Processing seeds: " + baseSeed + " to " + endSeed);
                for (long seed = baseSeed; seed <= endSeed; seed++) {
                    final long finalSeed = seed;
                    executor.submit(() -> {
                        long location = processSeed(finalSeed, almanac);
                        minLocation.updateAndGet(x -> Math.min(x, location));
                    });
                }
                System.out.println("Current Minimum Location: " + minLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // wait for all tasks to finish
        }

        System.out.println("Final Minimum Location: " + minLocation);
    }

    private static long processSeed(long seed, List<PriorityQueue<MapEntry4>> almanac) {
        List<Long> sourceList = new ArrayList<>();
        sourceList.add(seed);
        for (PriorityQueue<MapEntry4> map : almanac) {
            List<Long> destinationList = new ArrayList<>();
            for (long source : sourceList.stream().toList()) {
                for (MapEntry4 entry : map) {
                    if (source < entry.sourceStart) {
                        //System.out.println("source: " + source + ", entry.sourceStart: " + entry.sourceStart + ", entry.destinationStart: " + entry.destinationStart + ", entry.range: " + entry.range);
                        break;
                    }

                    if (source >= entry.sourceStart && source <= entry.sourceStart + entry.range) {
                        //System.out.println("source: " + source + ", entry.sourceStart: " + entry.sourceStart + ", entry.range: " + entry.range);
                        destinationList.add(entry.destinationStart + (source - entry.sourceStart));
                        sourceList.remove(source);
                        break;
                    }
                }
            }
            sourceList.addAll(destinationList);
        }
        return sourceList.stream().min(Long::compareTo).orElseThrow();
    }
}
