package com.olsson.aoc2021;

import java.util.Arrays;
import java.util.List;

public class Day20 implements Solution {

    /**
     * For the actual input, enhancement will flip all . to # on each iteration like a flicker
     * We use this even (and a flag to toggle the behavior) like a hacky way to simulate that behavior
     */
    boolean even = true;

    @Override
    public String part1(List<String> input) {
        var sum = solve(input, 2,true);
        return String.valueOf(sum);
    }

    // Allow flicker flag to be set as test input does not have that behavior
    public String test(List<String> input, int steps, boolean flicker) {
        var sum = solve(input, steps, flicker);
        return String.valueOf(sum);
    }

    @Override
    public String part2(List<String> input) {
        var sum = solve(input, 50, true);
        return String.valueOf(sum);
    }

    private Integer solve(List<String> input, int steps, boolean flicker) {
        String enhance = input.get(0);
        char[][] bufferedImage = parseImage(input.subList(2, input.size()));
        for (int i = 0; i < steps; i++) {
            bufferedImage = enhance(enhance, bufferedImage, flicker);
        }
        return Arrays.stream(bufferedImage)
                .map(chars -> {
                    int currentSum = 0;
                    for (char c: chars) {
                        if (c == '#')
                            currentSum++;
                    }
                    return currentSum;
                }).mapToInt(i -> i)
                .sum();
    }

    /**
     * Assumes square image
     * Increase the image size by 1 in all directions to enable growth of the image
     * Then simply read the offset value from previous image and interpret the binary string
     */
    private char[][] enhance(String enhance, char[][] image, boolean flicker) {
        var newWidthAndHeight = image.length + 2;
        var newImage = new char[newWidthAndHeight][newWidthAndHeight];
        for (int i = 0; i < newWidthAndHeight; i++) {
            for (int j = 0; j < newWidthAndHeight; j++) {
                var value = readEnhanceValueFrom(image, j - 1, i - 1);
                newImage[i][j] = enhance.charAt(value);
            }
        }
        if (flicker) {
            even = !even;
        }
        return newImage;
    }

    /**
     * Read 3x3 square at x,y to binary and return decimal
     */
    private int readEnhanceValueFrom(char[][] image, int x, int y) {
        StringBuilder builder = new StringBuilder();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                builder.append(readFromImage(image, j, i) == '#' ? "1" : "0");
            }
        }
        return Integer.parseInt(builder.toString(), 2);
    }

    /**
     * Would like to just default return . here but as the image flickers
     * between . and # in input data we need to return based on the iteration being a
     * even number or not
     */
    private char readFromImage(char[][] image, int x, int y) {
        try {
            return image[y][x];
        } catch (IndexOutOfBoundsException e) {
            return even ? '.' : '#';
        }
    }

    private char[][] parseImage(List<String> input) {
        var widthAndHeight = input.get(0).length();
        var image = new char[widthAndHeight][widthAndHeight];
        for (int i = 0; i < widthAndHeight; i++) {
            for (int j = 0; j < widthAndHeight; j++) {
                image[i][j] = input.get(i).charAt(j);
            }
        }
        return image;
    }

    @Override
    public String getDay() {
        return "20";
    }
}
