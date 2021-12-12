package com.olsson.aoc2021;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Day12 implements Solution {

    private static final String START = "start";

    @Override
    public String part1(List<String> input) {
        var caves = createCaverns(input);
        var start = getCave(caves, START);
        var paths = exploreCaves(start, caves, new Path("", new HashSet<>()), new HashSet<>(), false);
        return String.valueOf(paths.size());
    }

    @Override
    public String part2(List<String> input) {
        var caves = createCaverns(input);
        var start = getCave(caves, START);
        var paths = exploreCavesWithExtraVisit(start, caves, new Path("", new HashSet<>()), new HashSet<>())
                .stream()
                .map(p -> p.path)
                .distinct()
                .toList();
        return String.valueOf(paths.size());
    }


    /**
     * Iterate from start through all nodes and add all full paths to set
     * If extraVisit, do not add to visited nodes, allows us to visit a minor cave twice
     */
    private Set<Path> exploreCaves(Cave cave, Set<Cave> caves, Path path, Set<Path> toEnd, boolean extraVisit) {
        Cave end = null;
        Path newPath = path;
        if (!extraVisit) {
            newPath = path.add(cave);
        }
        var unexplored = unexploredCaves(cave, caves, path);
        for (Cave nextCave : unexplored) {
            // Delay end cave to get all Paths
            if (nextCave.name.equals("end")) {
                end = nextCave;
                continue;
            }
            exploreCaves(nextCave, caves, newPath, toEnd, false);
        }
        if (end != null) {
            toEnd.add(newPath.add(end));
        }
        return toEnd;
    }

    /**
     * In Part2 we try to visit each minor cave once without marking it as visited, thus allowing us to visit that one
     * twice, and then doing the normal exploration from that cave
     * This is done first in each cave that we explore the same way we do Part
     */
    private Set<Path> exploreCavesWithExtraVisit(Cave cave, Set<Cave> caves, Path path, Set<Path> fullPaths) {
        Cave end = null;
        var newPath = path.add(cave);
        var minor = getMinorCaves(cave, caves)
                .toList();
        for (Cave nextCave : minor) {
            exploreCaves(nextCave, caves, newPath.addWithoutVisit(nextCave), fullPaths, true);
        }
        var unexplored = unexploredCaves(cave, caves, path);
        for (Cave nextCave : unexplored) {
            // Delay end cave to get all Paths
            if (nextCave.name.equals("end")) {
                end = nextCave;
                continue;
            }
            exploreCavesWithExtraVisit(nextCave, caves, newPath, fullPaths);
        }
        if (end != null) {
            fullPaths.add(newPath.add(end));
        }
        return fullPaths;
    }

    private Stream<Cave> getMinorCaves(Cave cave, Set<Cave> caves) {
        return cave.neighbors.stream()
                .map(name -> getCave(caves, name))
                .filter(Cave::isMinor)
                .filter(theCave -> !theCave.name.equals(START) && !theCave.name.equals("end"));
    }

    private record Cave(String name, Set<String> neighbors) {
        private boolean isMinor() {
            return name.toLowerCase().equals(name);
        }

        private boolean fullyExplored(Set<String> visited) {
            return visited.containsAll(neighbors);
        }
    }

    private record Path(String path, Set<String> visited) {
        Path add(Cave cave) {
            var newVisited = new HashSet<>(this.visited);
            if (cave.isMinor()) {
                newVisited.add(cave.name);
            }
            return new Path(path + "," + cave.name, newVisited);
        }

        Path addWithoutVisit(Cave cave) {
            return new Path(path + "," + cave.name, new HashSet<>(visited));
        }
    }

    private Set<Cave> createCaverns(List<String> input) {
        var caves = new HashSet<Cave>();
        for (String s : input) {
            var fromAndTo = s.split("-");
            var from = existing(caves, fromAndTo[0]).orElseGet(() -> {
                var cave = new Cave(fromAndTo[0], new HashSet<>());
                caves.add(cave);
                return cave;
            });
            var to = existing(caves, fromAndTo[1]).orElseGet(() -> {
                var cave = new Cave(fromAndTo[1], new HashSet<>());
                caves.add(cave);
                return cave;
            });
            from.neighbors.add(fromAndTo[1]);
            to.neighbors.add(fromAndTo[0]);
        }
        return caves;
    }

    private Optional<Cave> existing(Set<Cave> created, String toFind) {
        return created.stream()
                .filter(cave -> cave.name.equals(toFind))
                .findFirst();
    }

    private Cave getCave(Set<Cave> created, String toFind) {
        return existing(created, toFind)
                .orElseThrow(() -> new IllegalArgumentException(toFind + ": no such cave exists"));
    }

    private List<Cave> unexploredCaves(Cave cave, Set<Cave> caves, Path path) {
        return cave.neighbors.stream()
                .map(name -> getCave(caves, name))
                .filter(nextCave -> !nextCave.fullyExplored(path.visited))
                .filter(canVisit(path))
                .toList();
    }

    private Predicate<Cave> canVisit(Path path) {
        return cave -> {
            if (cave.name.equals(START)) {
                return false;
            }
            if (cave.isMinor()) {
                return !path.visited.contains(cave.name);
            }
            return true;
        };
    }

    @Override
    public String getDay() {
        return "12";
    }
}
