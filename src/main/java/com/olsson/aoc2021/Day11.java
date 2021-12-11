package com.olsson.aoc2021;

import java.util.Arrays;
import java.util.List;

public class Day11 implements Solution {

    @Override
    public String part1(List<String> input) {
        return part1(input, 100);
    }

    public String part1(List<String> input, int steps) {
        var cave = new CavernMap(input);
        var flashes = 0;
        for (int i = 0; i < steps; i++) {
            flashes += cave.step();
        }
        return String.valueOf(flashes);
    }

    @Override
    public String part2(List<String> input) {
        var cave = new CavernMap(input);
        var step = 0;
        while(step < 10_000) {
            step++;
            if (cave.step() == 100) {
                return String.valueOf(step);
            }
        }
        return "Failed";
    }

    private static class CavernMap {

        private final int[][] squids = new int[10][10];
        private static final int SQUID_FLASH_LEVEL = 10;

        CavernMap(List<String> input) {
            for (int i = 0; i < input.size(); i++) {
                var currentRow = Arrays.stream(input.get(i).split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                squids[i] = currentRow;
            }
        }

        int step() {
            incrementAll();
            var flashes = checkFlashes();
            resetAfterFlash();
            return flashes;
        }

        private int flash (int i, int j) {
            var flashes = 1;
            squids[i][j]++;
            for (var row = i - 1; row <= i + 1; row++) {
                for (var column = j - 1; column <= j + 1; column++) {
                    if (row == i && column == j) {
                        continue;
                    }
                    update(row, column);
                    var squid = getSquid(row, column);
                    if (squid == SQUID_FLASH_LEVEL || squid == SQUID_FLASH_LEVEL + 1) {
                        flashes += flash(row, column);
                    }
                }
            }
            return flashes;
        }

        private void incrementAll() {
            for (var row = 0; row < 10; row++) {
                for (var column = 0; column < 10; column++) {
                    squids[row][column]++;
                }
            }
        }

        private int checkFlashes() {
            var flashes = 0;
            for (var row = 0; row < 10; row++) {
                for (var column = 0; column < 10; column++) {
                    if(squids[row][column] == SQUID_FLASH_LEVEL) {
                        flashes += flash(row, column);
                    }
                }
            }
            return flashes;
        }

        private void resetAfterFlash() {
            for (var row = 0; row < 10; row++) {
                for (var column = 0; column < 10; column++) {
                    if (squids[row][column] >= SQUID_FLASH_LEVEL) {
                        squids[row][column] = 0;
                    }
                }
            }
        }

        private void update(int i, int j) {
            try {
                squids[i][j] += 1;
            } catch (IndexOutOfBoundsException ex) {
                //Ignore
            }
        }

        private int getSquid (int i, int j){
            try {
                return squids[i][j];
            } catch (IndexOutOfBoundsException e) {
                return -1;
            }
        }
    }

    @Override
    public String getDay() {
        return "11";
    }
}
