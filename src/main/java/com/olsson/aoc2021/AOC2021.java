package com.olsson.aoc2021;

import java.util.List;

public class AOC2021 {

    private static void run() {
        List.of(
                new Day01(),
                new Day02()
        ).forEach(Solution::solve);
    }

    public static void main(String[] args) {
        AOC2021.run();
    }
}
