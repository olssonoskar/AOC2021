package com.olsson.aoc2021;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day17 implements Solution{

    @Override
    public String part1(List<String> input) {
        var area = Area.from(input.get(0));
        return String.valueOf(IntStream.range(0, Math.abs(area.fromY)).sum());
    }

    @Override
    public String part2(List<String> input) {
        var area = Area.from(input.get(0));
        var xs = findAllX(area);
        var ys = findAllY(area);
        var combinations = allToHitTarget(xs, ys);
        return String.valueOf(combinations.size());
    }

    /**
     * Match any combination where x and y hit target during the same step
     * or where x has stopped moving and y simply falls through target for any step higher than x hit
     */
    private List<InitVelocity> allToHitTarget(List<Result> allX, List<Result> allY) {
        var combinations = new ArrayList<InitVelocity>();
        for (Result x: allX) {
            var matches = allY.stream()
                    .filter(y -> y.stepsToHit == x.stepsToHit || (x.stoppedWithinTarget && x.stepsToHit < y.stepsToHit()))
                    .map(y -> new InitVelocity(x.initial, y.initial))
                    .toList();
            combinations.addAll(matches);
        }
        return combinations.stream()
                .distinct()
                .toList();
    }

    private List<Result> findAllY(Area area) {
        var possibleY = new ArrayList<Result>();
        for (var y = area.fromY; y < Math.abs(area.fromY); y++ ) {
            int speed = y;
            int position = 0;
            int steps = 0;
            while(position > area.fromY) {
                position = position + speed;
                speed -= 1;
                steps += 1;
                if (position >= area.fromY && position <= area.toY) {
                    possibleY.add(new Result(y, steps, false));
                }
            }
        }
        return possibleY;
    }

    private List<Result> findAllX(Area area) {
        var possibleX = new ArrayList<Result>();
        for (int i = area.toX; i > 1; i--) {
            var result = findStepsWithinTarget(i, area.fromX, area.toX);
            possibleX.addAll(result);
        }
        return possibleX;
    }

    private List<Result> findStepsWithinTarget(int initialSpeed, int targetMin, int targetMax) {
        int sum = 0;
        var all = new ArrayList<Result>();
        for (int i = 0; sum < targetMax && initialSpeed >= i; i++) {
            sum += initialSpeed - i;
            if (sum >= targetMin && sum <= targetMax) {
                all.add(new Result(initialSpeed, i + 1, initialSpeed <= i));
            }
        }
        return all;

    }

    private record Result(int initial, int stepsToHit, boolean stoppedWithinTarget) { }
    private record InitVelocity(int x, int y) {}
    private record Area(int fromX, int toX, int fromY, int toY) {


        static Area from(String input) {
            var split = input.split(",");
            var splitX = split[0].split("\\.\\.");
            var splitY = split[1].split("\\.\\.");
            return new Area(
                    extractNumber(splitX[0]),
                    extractNumber(splitX[1]),
                    extractNumber(splitY[0]),
                    extractNumber(splitY[1])
            );
        }
    }

    private static int extractNumber(String input) {
        var result = Integer.parseInt(input.chars()
                .filter(Character::isDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append).toString());
        if (input.contains("-")) {
            return -result;
        }
        return result;
    }

    @Override
    public String getDay() {
        return "17";
    }
}
