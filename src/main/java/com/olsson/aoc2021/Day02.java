package com.olsson.aoc2021;

import java.util.List;

public class Day02 implements Solution{

    public static final String SPACE = " ";

    @Override
    public String part1(List<String> input) {
        var sub = new Submarine(0, 0, 0);
        for (var command: input) {
            var directionAndUnits = command.split(SPACE);
            var units = Integer.parseInt(directionAndUnits[1]);
            sub = switch (directionAndUnits[0]) {
                case "forward" -> sub.moveX(units);
                case "down" -> sub.moveY(units);
                case "up" -> sub.moveY(-units);
                default -> throw new IllegalArgumentException("Unknown command: " + directionAndUnits[0]);
            };
        }
        return String.valueOf(sub.x * sub.y);
    }

    @Override
    public String part2(List<String> input) {
        var sub = new Submarine(0, 0, 0);
        for (var command: input) {
            var directionAndUnits = command.split(SPACE);
            var units = Integer.parseInt(directionAndUnits[1]);
            sub = switch (directionAndUnits[0]) {
                case "forward" -> sub.moveBasedOnAim(units);
                case "down" -> sub.pitch(units);
                case "up" -> sub.pitch(-units);
                default -> throw new IllegalArgumentException("Unknown command: " + directionAndUnits[0]);
            };
        }
        return String.valueOf(sub.x * sub.y);
    }

    @Override
    public String getDay() {
        return "02";
    }

    /**
     * x - vertical, y - depth, aim - for part2
     */
    private record Submarine(int x, int y, int aim) {
        Submarine moveX(int units) {
            return new Submarine(x + units, y, aim);
        }

        Submarine moveY(int units) {
            return new Submarine(x, y + units, aim);
        }

        Submarine moveBasedOnAim(int units) {
            return new Submarine(x + units, y + (units * aim), aim);
        }

        Submarine pitch(int units) {
            return new Submarine(x, y, aim + units);
        }
    }
}
