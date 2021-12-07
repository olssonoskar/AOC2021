package com.olsson.aoc2021;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day07 implements Solution {


    @Override
    public String part1(List<String> input) {
        return String.valueOf(solve(input, constantFuel).amount);
    }

    @Override
    public String part2(List<String> input) {
        return String.valueOf(solve(input, incrementalFuel).amount);
    }

    private FuelRequired solve(List<String> input, IntBinaryOperator consumption) {
        var crabPosition = input.stream().map(Integer::parseInt).toList();
        return minToMax(crabPosition)
                .map(pos -> new FuelRequired(pos, countReqFuel(pos, crabPosition, consumption)))
                .min(Comparator.comparingInt(fuel -> fuel.amount))
                .orElseThrow(() -> new IllegalArgumentException("No solution"));
    }

    private int countReqFuel(int x, List<Integer> crabPosition, IntBinaryOperator consumption) {
        return crabPosition.stream()
                .map(pos -> consumption.applyAsInt(pos, x))
                .reduce(Integer::sum)
                .orElseThrow(() -> new ArithmeticException("Nothing to sum"));
    }

    IntBinaryOperator constantFuel = (crabPosition, target) -> Math.abs(crabPosition - target);

    IntBinaryOperator incrementalFuel = (crabPosition, target) -> {
            var steps = Math.abs(crabPosition - target);
            int totalFuel = 0;
            for (int i = 0; i <= steps; i++) {
                totalFuel += i;
            }
            return totalFuel;
        };


    private Stream<Integer> minToMax(List<Integer> input) {
        var min = input.stream()
                .mapToInt(i -> i)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("No minimum"));
        var max = input.stream()
                .mapToInt(i -> i)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("No maximum"));
        return IntStream.range(min, max).boxed();
    }

    private record FuelRequired(int toPosition, int amount) {}

    @Override
    public String getDay() {
        return "07";
    }

    @Override
    public List<String> getInput() {
        return Arrays.stream(inputParser.splitLines(getDay(), ",").get(0)).toList();
    }
}
