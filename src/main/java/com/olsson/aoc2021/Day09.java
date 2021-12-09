package com.olsson.aoc2021;

import java.util.*;
import java.util.stream.Stream;

public class Day09 implements Solution {


    @Override
    public String part1(List<String> input) {
        var lowPoints = new HeightMap(input).getLowPoints();
        return String.valueOf(
                lowPoints.stream()
                        .mapToInt(low -> low.height + 1)
                        .sum()
        );
    }

    @Override
    public String part2(List<String> input) {
        var map = new HeightMap(input);
        var basins = map.getLowPoints().stream()
                .map(p -> map.exploreBasin(p, new HashSet<>()).size())
                .mapToInt(i -> i)
                .sorted()
                .toArray();
        return String.valueOf(basins[basins.length-1] * basins[basins.length-2] * basins[basins.length-3]);
    }

    private static class HeightMap {

        private final int[][] heights;
        private final int width;
        private final int height;

        HeightMap(List<String> input) {
            int[][] heightsMap = new int[input.size()][input.get(0).length()];
            for (int i = 0; i < input.size(); i++) {
                var currentRow = Arrays.stream(input.get(i).split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                heightsMap[i] = currentRow;
            }
            this.heights = heightsMap;
            this.height = heightsMap.length;
            this.width = heightsMap[0].length;
        }

        List<Point> getLowPoints() {
            var lowPoints = new ArrayList<Point>();
            for (int i = 0; i < height ; i++) {
                for (var j = 0; j < width; j++) {
                    var currentCell = read(i, j);
                    var adjacentIsLarger =
                            Stream.of(read(i + 1, j), read(i - 1, j), read(i, j + 1), read(i, j - 1))
                            .allMatch(adjacent -> adjacent > currentCell);
                    if (adjacentIsLarger) {
                        lowPoints.add(new Point(currentCell, i, j));
                    }
                }
            }
            return lowPoints;
        }

        private Set<Point> exploreBasin(Point point, Set<Point> explored) {
            explored.add(point);
            for(Point p : List.of(getPoint(point.i + 1, point.j),
                            getPoint(point.i - 1, point.j),
                            getPoint(point.i, point.j + 1),
                            getPoint(point.i, point.j - 1))) {
                if (p.height < 9 && !explored.contains(p)) {
                        exploreBasin(p, explored);
                }
            }
            return explored;
        }

        private int read(int i, int j) {
            try {
                return heights[i][j];
            } catch (IndexOutOfBoundsException e) {
                return 10;
            }
        }

        private Point getPoint(int i, int j) {
            return new Point(read(i, j), i, j);
        }
    }

    private record Point(int height, int i, int j) {}

    @Override
    public String getDay() {
        return "09";
    }
}
