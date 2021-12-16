package com.olsson.aoc2021;

import java.util.*;
import java.util.stream.Stream;

public class Day15 implements Solution {

    int[][] cave;

    /**
     * Negative 1 for both parts as we start in the first risk zone which is not counted in assignment
     */

    @Override
    public String part1(List<String> input) {
        cave = generate(input);
        var low = findLowRiskPathTo(new Point(input.size() -1 , input.size() -1));
        return String.valueOf(low - 1);
    }

    @Override
    public String part2(List<String> input) {
        cave = generateExpanded(input);
        var goal = new Point((input.size() * 5) - 1, (input.get(0).length() * 5) - 1);
        var low = findLowRiskPathTo(goal);
        return String.valueOf(low - 1);
    }

    // Basically Dijkstra, returns result only
    private int findLowRiskPathTo(Point goal) {
        var toCheck = new PriorityQueue<>(new RiskComparator());
        var map = new HashMap<Point, Risk>();
        var first = new Point(0, 0);
        map.put(first, new Risk(first, 1, 1));
        toCheck.add(new Risk(first, 1, 1));
        var visited = new HashSet<Point>();

        while(!toCheck.isEmpty()) {
            var current = toCheck.poll();
            if (current.pos.x == goal.x && current.pos.y == goal.y) {
                return map.get(current.pos).total;
            }
            if (visited.contains(current.pos)) {
                continue;
            }
            neighbors(current.pos, map)
                    .filter(risk -> !visited.contains(risk.pos))
                    .forEach(riskNode -> {
                        var nodeToCheck = riskNode;
                        if (riskNode.risk + current.total() < riskNode.total) {
                            nodeToCheck = new Risk(riskNode.pos, riskNode.risk + current.total, riskNode.risk);
                            map.put(riskNode.pos, nodeToCheck);
                        }
                        toCheck.add(nodeToCheck);
                    });
            visited.add(current.pos);
        }
        return 0;
    }

    Stream<Risk> neighbors(Point p, Map<Point, Risk> map) {
        return Stream.of(
                new Point(p.x + 1, p.y),
                new Point(p.x - 1, p.y),
                new Point(p.x, p.y + 1),
                new Point(p.x, p.y - 1))
                .map(pos -> map.getOrDefault(pos, new Risk(pos, Integer.MAX_VALUE, readRisk(pos.x, pos.y))))
                .filter(risk -> risk.risk != -1);
    }

    private int readRisk(int x, int y) {
        try {
            return cave[y][x];
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static class RiskComparator implements Comparator<Risk> {
        @Override
        public int compare(Risk o1, Risk o2) {
            return Integer.compare(o1.total, o2.total);
        }
    }

    private record Risk(Point pos, int total, int risk) {}
    private record Point(int x, int y) { }

    /**
     * Generates an expanded view of the input (5 times both vertically and horizontally)
     */
    private int[][] generateExpanded(List<String> input) {
        var partialHeight = input.size();
        var partialWidth = input.get(0).length();
        var points = new int[partialHeight * 5][partialWidth * 5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                for (int i = 0; i < input.size(); i++) {
                    for (int k = 0; k < input.get(0).length(); k++) {
                        var risk = incrementedRisk(Integer.parseInt(input.get(i).substring(k, k + 1)), x, y);
                        points[i + (y * partialHeight)][k + (x * partialWidth)] = risk;
                    }
                }
            }
        }
        points[0][0] = Integer.parseInt(input.get(0).substring(0, 1));
        return points;
    }

    /**
     * Increments risk on new generations, wraps post 9 back to 1
     */
    private int incrementedRisk(int risk, int x, int y) {
        var riskUpdated = risk + x + y;
        if (riskUpdated > 9) {
            return riskUpdated - 9;
        }
        return riskUpdated;
    }

    private int[][] generate(List<String> input) {
        var points = new int[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int k = 0; k < input.get(0).length(); k++) {
                var risk = Integer.parseInt(input.get(i).substring(k, k + 1));
                if (i == 0 && k == 0) {
                   points[0][0] = risk;
                } else {
                    points[i][k] = risk;
                }
            }
        }
        return points;
    }

    @Override
    public String getDay() {
        return "15";
    }
}
