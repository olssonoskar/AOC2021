import com.olsson.aoc2021.Day20;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Tests {

    @Test
    void part1Example() {
        var day = new Day20();
        assertEquals("35", day.test(example.lines().toList(), 2, false));
    }

    @Test
    void part1Solution() {
        var day = new Day20();
        assertEquals("5044", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var day = new Day20();
        assertEquals("3351", day.test(example.lines().toList(), 50, false));
    }


    @Test
    void part2Solution() {
        var day = new Day20();
        assertEquals("18074", day.part2(day.getInput()));
    }

    String example = """
            ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#
                        
            #..#.
            #....
            ##..#
            ..#..
            ..###
            """;

}
