package com.olsson.aoc2021;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Part 1 - find most common bit at each pos to form new binary (gamma) and multiply bt its inverse
 * Part 2 - find singular binaryStr from filtering most common and least common bit at each index for remaining binary
 */
public class Day03 implements Solution {

    private static final char ONE = '1';

    @Override
    public String part1(List<String> input) {
        var gammaRate = new StringBuilder();
        var bitLength = input.get(0).length();
        for (int i = 0; i < bitLength; i++) {
            gammaRate.append(mostCommonAtIndex(input, i));
        }
        return String.valueOf(convertFromBinary(gammaRate.toString()) * convertFromBinary(invert(gammaRate.toString())));
    }

    @Override
    public String part2(List<String> input) {
        var o2 = findO2GenRating(input);
        var co2 = findCO2ScrubRating(input);
        return String.valueOf(convertFromBinary(o2) * convertFromBinary(co2));
    }

    private char mostCommonAtIndex(List<String> input, int index) {
        var ones = 0;
        for (String bits : input) {
            if (bits.charAt(index) == ONE) {
                ones++;
            }
        }
        if (ones >= (input.size() / 2d)) {
            return '1';
        }
        return '0';
    }

    private char leastCommonAtIndex(List<String> input, int index) {
        return flip(mostCommonAtIndex(input, index));
    }

    private String findO2GenRating(List<String> input) {
        return findRating(input, this::mostCommonAtIndex);
    }

    private String findCO2ScrubRating(List<String> input) {
        return findRating(input, this::leastCommonAtIndex);
    }

    private String findRating(List<String> ratings, BiFunction<List<String>, Integer, Character> findExpectedBit) {
        var bitLength = ratings.get(0).length();
        for (int i = 0; i < bitLength; i++) {
            var currentBit = findExpectedBit.apply(ratings, i);
            ratings = filterRatings(ratings, currentBit, i);
            if (ratings.size() == 1) {
                return ratings.get(0);
            }
        }
        throw new IllegalArgumentException("Singular rating was not found");
    }

    private List<String> filterRatings(List<String> candidates, char required, int atIndex) {
        return candidates.stream()
                .filter(s -> s.charAt(atIndex) == required)
                .toList();
    }

    private int convertFromBinary(String binary) {
        return Integer.parseInt(binary, 2);
    }

    private String invert(String binary) {
        return binary.replace('0', 'x')
                .replace('1', '0')
                .replace('x', '1');
    }

    private char flip(char bit) {
        if (bit == ONE) {
            return '0';
        }
        return ONE;
    }

    @Override
    public String getDay() {
        return "03";
    }
}
