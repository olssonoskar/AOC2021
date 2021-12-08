package com.olsson.aoc2021;

import java.util.*;

public class Day08 implements Solution {

    private final List<Integer> uniqueSegmentLengths = List.of(2,3,4,7);
    private static final String FAILURE = "Analysis failed";

    @Override
    public String part1(List<String> input) {
        var uniqueSegmentOccurrences = input.stream()
                .map(entry -> entry.split("\\|")[1])
                .flatMap(output -> Arrays.stream(output.split(" ")))
                .filter(s -> !s.isBlank())
                .filter(s -> uniqueSegmentLengths.contains(s.length()))
                .count();
        return String.valueOf(uniqueSegmentOccurrences);
    }

    @Override
    public String part2(List<String> input) {
       return String.valueOf(input.stream()
               .map(this::entryToInteger)
               .mapToLong(i -> i)
               .sum());

    }

    private Integer entryToInteger(String entry) {
        var numbersAndOutput = entry.split("\\|");
        var digits = decode(numbersAndOutput[0]);
        StringBuilder out = new StringBuilder();
        for (String digit: numbersAndOutput[1].split(" ")) {
            if (digit.isBlank()) {
                continue;
            }
            out.append(digits.stream()
                    .filter(decodedDigit -> disjointUnion(0, digit, decodedDigit.pattern))
                    .map(decodedDigit -> decodedDigit.value)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(digit + ": None matching")));
        }
        return Integer.parseInt(out.toString());
    }

    private List<Digit> decode(String entry) {
        var patterns = Arrays.stream(entry.split(" "))
                .filter(s -> !s.isBlank())
                .toList();
        var one = findWithLength(patterns, 2).get(0);
        var four = findWithLength(patterns, 4).get(0);
        var seven = findWithLength(patterns, 3).get(0);
        var eight = findWithLength(patterns, 7).get(0);
        var nine = findWithLength(patterns, 6).stream()
                .filter(candidate -> containsAllOf(candidate, four) && containsAllOf(candidate, seven))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(FAILURE));
        var six = findWithLength(patterns, 6).stream()
                .filter(candidate -> !containsAllOf(candidate, one))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(FAILURE));
        var zero = findWithLength(patterns, 6).stream()
                .filter(candidate -> !candidate.equals(six) && !candidate.equals(nine))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(FAILURE));
        var five = findWithLength(patterns, 5).stream()
                .filter(candidate -> disjointUnion(1, six, candidate))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(FAILURE));
        var two = findWithLength(patterns, 5).stream()
                .filter(candidate -> disjointUnion(3, nine, candidate))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(FAILURE));
        var three = findWithLength(patterns, 5).stream()
                .filter(candidate -> !candidate.equals(two) && !candidate.equals(five))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(FAILURE));
        return List.of(
                new Digit(zero, "0"),
                new Digit(one, "1"),
                new Digit(two, "2"),
                new Digit(three, "3"),
                new Digit(four, "4"),
                new Digit(five, "5"),
                new Digit(six, "6"),
                new Digit(seven, "7"),
                new Digit(eight, "8"),
                new Digit(nine, "9"));
    }

    @Override
    public String getDay() {
        return "08";
    }

    private List<String> findWithLength(List<String> input, int length) {
        return input.stream()
                .filter(s -> s.length() == length)
                .toList();
    }

    private boolean containsAllOf(String first, String other) {
        for (int i = 0; i < other.length(); i++) {
            if (!first.contains(other.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean disjointUnion(int expected, String first, String second) {
        var common = new HashSet<>(Arrays.stream(first.split("")).toList());
        common.retainAll(Arrays.stream(second.split("")).toList());

        var all = new HashSet<>(Arrays.stream(first.split("")).toList());
        all.addAll(Arrays.stream(second.split("")).toList());
        all.removeAll(common);

        return expected == all.size();
    }

    private record Digit(String pattern, String value) {}
}
