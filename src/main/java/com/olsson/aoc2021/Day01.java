package com.olsson.aoc2021;

import java.util.ArrayList;
import java.util.List;

public class Day01 implements Solution{

    @Override
    public String part1(List<String> input) {
        return String.valueOf(countIncreases(convertToInt(input)));
    }

    @Override
    public String part2(List<String> input) {
        var windows = new ArrayList<Integer>();
        var measurements = convertToInt(input);
        for (var i = 0; i < measurements.size() - 2; i++){
            windows.add(measurements.subList(i, i + 3)
                    .stream()
                    .reduce(Integer::sum)
                    .orElseThrow(() -> new IllegalArgumentException("Unable to sum input")));
        }
        return String.valueOf(countIncreases(windows));
    }

    private int countIncreases(List<Integer> depthValues) {
        var count = 0;
        for (var i = 1; i < depthValues.size(); i++) {
            if (depthValues.get(i) > depthValues.get(i - 1)) {
                count++;
            }
        }
        return count;
    }

    private List<Integer> convertToInt(List<String> input) {
        return input
                .stream().map(Integer::parseInt)
                .toList();
    }

    @Override
    public String getDay() {
        return "01";
    }
}
