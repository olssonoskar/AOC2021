package com.olsson.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day06 implements Solution {

    private static final int FISH_READY_TO_SPAWN = 0;
    private static final int NEW_FISH_SPAWN_TIMER = 8;

    @Override
    public String part1(List<String> input) {
        return part1(input, 80);
    }

    @Override
    public String part2(List<String> input) {
        return runSimulation(initState(input), 256);
    }

    public String part1(List<String> input, int simulationDays) {
        var anglerFish = initState(input);
        return runSimulation(anglerFish, simulationDays);
    }

    private String runSimulation(Map<Integer, Long> anglerFish, int simulationDays) {
        for (int i = 0; i < simulationDays; i++) {
            var spawns = anglerFish.getOrDefault(FISH_READY_TO_SPAWN, 0L);
            anglerFish = anglerFish.entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> reduceDay(entry.getKey()),
                            Map.Entry::getValue,
                            Long::sum
                    ));
            anglerFish.put(NEW_FISH_SPAWN_TIMER, spawns);
        }
        return String.valueOf(anglerFish.values().stream()
                .reduce(Long::sum)
                .orElseThrow(() -> new IllegalArgumentException("No values to sum")));
    }

    private int reduceDay(int current) {
        if (current > 0) {
            return current - 1;
        }
        return 6;
    }

    private Map<Integer, Long> initState(List<String> input) {
        return input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toMap(
                        daysToNewSpawn -> daysToNewSpawn,
                        unused -> 1L,
                        Long::sum
                ));
    }

    @Override
    public String getDay() {
        return "06";
    }

    @Override
    public List<String> getInput() {
        return Arrays.stream(inputParser.splitLines(getDay(), ",").get(0)).toList();
    }
}
