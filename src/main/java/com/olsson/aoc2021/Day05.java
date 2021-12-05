package com.olsson.aoc2021;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day05 implements Solution {

    @Override
    public String part1(List<String> input) {
        var horizontalOrVertical = parseVents(input).stream()
                .filter(vent -> vent.from.x == vent.to.x || vent.from.y == vent.to.y)
                .toList();
        var overlapPoints = countOverlaps(horizontalOrVertical);
        return String.valueOf(overlapPoints);
    }

    @Override
    public String part2(List<String> input) {
        var overlapPoints = countOverlaps(parseVents(input));
        return String.valueOf(overlapPoints);
    }

    private Long countOverlaps(List<Vent> vents) {
        return vents.stream()
                .flatMap(vent -> vent.line().stream())
                .collect(Collectors.toMap(
                        point -> point,
                        point -> 1,
                        Integer::sum)
                ).entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .count();
    }

    private List<Vent> parseVents(List<String> input) {
        return input.stream()
                .map(s -> {
                    var fromAndTo = s.split(" ");
                    var from = fromAndTo[0].split(",");
                    var to = fromAndTo[2].split(",");
                    return new Vent(
                            new Point(Integer.parseInt(from[0]), Integer.parseInt(from[1])),
                            new Point(Integer.parseInt(to[0]), Integer.parseInt(to[1]))
                    );
                }).toList();
    }

    @Override
    public String getDay() {
        return "05";
    }


    private record Vent(Point from, Point to) {
        List<Point> line() {
            var diffX = to.x - from.x;
            if (diffX == 0) {
                return vertical();
            }
            var k = (to.y - from.y) / diffX;
            var m = from.y - (k * from.x);

            return getRange(from.x, to.x)
                    .map(x -> new Point(x, k * x + m))
                    .toList();
        }

        private Stream<Integer> getRange(int first, int second) {
            if (first > second) {
                return IntStream.range(second, first + 1).boxed();
            }
            return IntStream.range(first, second + 1).boxed();
        }

        private List<Point> vertical() {
            return getRange(from.y, to.y)
                    .map(y -> new Point(from.x, y))
                    .toList();
        }
    }

    private record Point(int x, int y) {}
}
