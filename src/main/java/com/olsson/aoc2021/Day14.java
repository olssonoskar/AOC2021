package com.olsson.aoc2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 implements Solution {

    @Override
    public String part1(List<String> input) {
        return run(input, 10);
    }

    @Override
    public String part2(List<String> input) {
        return run(input, 40);
    }

    private String run(List<String> input, int steps) {
        var template = input.get(0);
        var expansions = createExpansionMap(input);
        var templatePairs = initialPairs(template);
        for (var i = 0; i < steps; i++) {
            templatePairs = convertPairs(templatePairs, expansions);
        }
        return String.valueOf(evaluate(templatePairs, template.charAt(template.length() - 1)));
    }

    private Map<Pair, Long> convertPairs(Map<Pair, Long> pairCount, Map<String, String> addition) {
        Map<Pair, Long> next = new HashMap<>();
        for (Map.Entry<Pair, Long> entry: pairCount.entrySet()) {
            var toInsert = addition.get(entry.getKey().representation()).charAt(0);
            var firstPair = new Pair(entry.getKey().first(), toInsert);
            var secondPair = new Pair(toInsert, entry.getKey().second());
            next.merge(firstPair, entry.getValue(), Long::sum);
            next.merge(secondPair, entry.getValue(), Long::sum);
        }
        return next;
    }

    private long evaluate(Map<Pair, Long> template, char last) {
        var count = template.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().first,
                        Map.Entry::getValue,
                        Long::sum));
        count.merge(last, 1L, Long::sum);
        return count.values().stream().max(Long::compare).orElse(0L) -
                count.values().stream().min(Long::compare).orElse(0L);
    }

    private Map<String, String> createExpansionMap(List<String> input) {
        var pairToAddition = new HashMap<String, String>();
        for (int i = 2; i < input.size(); i++) {
            var split = input.get(i).split(" -> ");
            pairToAddition.put(split[0], split[1]);
        }
        return pairToAddition;
    }

    private Map<Pair, Long> initialPairs(String input) {
        var pairs = new HashMap<Pair, Long>();
        for (int i = 0; i < input.length() - 1; i++) {
            pairs.merge(new Pair(input.charAt(i), input.charAt(i + 1)),
                    1L,
                    Long::sum);
        }
        return pairs;
    }

    private record Pair(char first, char second) {
        private String representation() {
            return String.valueOf(first) + second;
        }
    }

    @Override
    public String getDay() {
        return "14";
    }
}
