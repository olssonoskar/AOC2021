package com.olsson.aoc2021;

import java.util.List;

public interface Solution {

    InputParser inputParser = new InputParser();

    @SuppressWarnings("java:S106")
    default void solve() {
        System.out.println("---------- Day " + getDay() + " ----------");
        System.out.println("Part 1 = " + part1(getInput()));
        System.out.println("Part 2 = " + part2(getInput()));
    }

    default List<String> getInput() {
        return inputParser.lines(getDay());
    }

    String part1(List<String> input);
    String part2(List<String> input);
    String getDay();

}
