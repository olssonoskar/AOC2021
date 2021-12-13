package com.olsson.aoc2021;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 implements Solution {
    @Override
    public String part1(List<String> input) {
        var paper = new Paper(input.stream().takeWhile(s -> !s.isBlank()).toList());
        var fold = input.stream()
                .filter(s -> s.contains("fold"))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No fold operations"));
        if (fold.contains("y")) {
            paper.foldHorizontal(Integer.parseInt(fold.split("=")[1]));
        } else {
            paper.foldVertical(Integer.parseInt(fold.split("=")[1]));
        }
        return String.valueOf(paper.dots.size());
    }

    @Override
    public String part2(List<String> input) {
        var paper = new Paper(input.stream().takeWhile(s -> !s.isBlank()).toList());
        var folds = input.stream()
                .filter(s -> s.contains("fold"))
                .toList();
        for (String fold: folds) {
            if (fold.contains("y")) {
                paper.foldHorizontal(Integer.parseInt(fold.split("=")[1]));
            } else {
                paper.foldVertical(Integer.parseInt(fold.split("=")[1]));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            for (int k = 0; k < 40; k++) {
                if (paper.dots.contains(new Point(k, i))) {
                    sb.append("#");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static class Paper {

        private Set<Point> dots;

        Paper(List<String> input) {
            dots = input.stream()
                    .map(s -> {
                        var xAndY = s.split(",");
                        return new Point(
                                Integer.parseInt(xAndY[0]),
                                Integer.parseInt(xAndY[1]));
                    }).collect(Collectors.toSet());
        }

        private void foldHorizontal(int alongY) {
            dots = dots.stream()
                    .map(point -> {
                        if(point.y < alongY) {
                            return point;
                        }
                        var diff = point.y - alongY;
                        return new Point(point.x, alongY - diff);
                    }).collect(Collectors.toSet());
        }

        private void foldVertical(int alongX) {
            dots = dots.stream()
                    .map(point -> {
                        if(point.x < alongX) {
                            return point;
                        }
                        var diff = point.x - alongX;
                        return new Point(alongX - diff, point.y);
                    }).collect(Collectors.toSet());
        }
    }

    private record Point(int x, int y) {}

    @Override
    public String getDay() {
        return "13";
    }
}
