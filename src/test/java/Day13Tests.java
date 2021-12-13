import com.olsson.aoc2021.Day13;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Tests {

    @Test
    void part1Example() {
        var day = new Day13();
        assertEquals("17", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day13();
        assertEquals("790", day.part1(day.getInput()));
    }

    @Test
    void part2Solution() {
        var day = new Day13();
        assertEquals(part2, day.part2(day.getInput()));
    }

    String part2 = """
            ###...##..#..#.####.###..####...##..##..
            #..#.#..#.#..#....#.#..#.#.......#.#..#.
            #..#.#....####...#..###..###.....#.#....
            ###..#.##.#..#..#...#..#.#.......#.#....
            #....#..#.#..#.#....#..#.#....#..#.#..#.
            #.....###.#..#.####.###..#.....##...##..
            ........................................
            """;

    String example = """
            6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0
                        
            fold along y=7
            fold along x=5
            """;

}
