import com.olsson.aoc2021.Day11;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Tests {

    String example = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
            """;

    @Test
    void part1Example10() {
        var day = new Day11();
        assertEquals("204", day.part1(example.lines().toList(), 10));
    }

    @Test
    void part1Example100() {
        var day = new Day11();
        assertEquals("1656", day.part1(example.lines().toList(), 100));
    }

    @Test
    void part1Solution() {
        var day = new Day11();
        assertEquals("1773", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var day = new Day11();
        assertEquals("195", day.part2(example.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day11();
        assertEquals("494", day.part2(day.getInput()));
    }

}
