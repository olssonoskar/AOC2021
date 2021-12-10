package com.olsson.aoc2021;

import java.util.*;
import java.util.stream.Collectors;

public class Day10 implements Solution {

    private final Set<Character> opener = Set.of('(', '[', '{', '<');
    private final Set<Character> closer = Set.of(')', ']', '}', '>');
    private static final String CORRUPT = "CORRUPT";
    private static final String INCOMPLETE = "INCOMPLETE";

    @Override
    public String part1(List<String> input) {
        var result = input.stream()
                .map(this::corruptOrIncomplete)
                .filter(line -> line.status.equals(CORRUPT))
                .map(line -> line.value)
                .collect(Collectors.toMap(
                        s -> s,
                        s -> 1,
                        Integer::sum
                ));
        return String.valueOf(
                result.getOrDefault(")", 0) * 3 +
                        result.getOrDefault("]", 0) * 57 +
                        result.getOrDefault("}", 0) * 1197 +
                        result.getOrDefault(">", 0) * 25137
        );
    }

    private LineResult corruptOrIncomplete(String line) {
        var previous = new LinkedList<Character>();
        for (int i = 0; i < line.length(); i++) {
            var current = line.charAt(i);
            if (opener.contains(current)) {
                previous.push(current);
            } else if (closer.contains(current)) {
                if (closingFor(previous.pop()) != current) {
                    return new LineResult(CORRUPT, String.valueOf(current));
                }
            } else {
                throw new IllegalArgumentException("Unrecognized char");
            }
        }
        if (!previous.isEmpty()) {
            return new LineResult(INCOMPLETE, toComplete(previous));
        }
        return new LineResult("Valid", "");
    }

    private String toComplete(List<Character> openers) {
        return openers.stream()
                .map(this::closingFor)
                .map(String::valueOf)
                .reduce((first, second) -> first + second)
                .orElseThrow(() -> new IllegalArgumentException("No valid completion string"));
    }

    private char closingFor(char open) {
        return switch (open) {
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            case '<' -> '>';
            default -> throw new IllegalArgumentException("Not a valid opener");
        };
    }

    @Override
    public String part2(List<String> input) {
        var result = input.stream()
                .map(this::corruptOrIncomplete)
                .filter(line -> line.status.equals(INCOMPLETE))
                .map(line -> line.value)
                .map(this::scoreCompletion)
                .sorted()
                .toList();
        return String.valueOf(result.get(result.size() / 2));
    }

    private long scoreCompletion(String toComplete) {
        long score = 0L;
        for (int i = 0; i < toComplete.length(); i++) {
            score *= 5;
            score += switch (toComplete.charAt(i)) {
                case ')' -> 1;
                case ']' -> 2;
                case '}' -> 3;
                case '>' -> 4;
                default -> throw new IllegalArgumentException("Not a valid closer");
            };
        }
        return score;
    }

    private record LineResult(String status, String value) { }

    @Override
    public String getDay() {
        return "10";
    }
}
