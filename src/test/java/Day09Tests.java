import com.olsson.aoc2021.Day09;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Tests {

    String example = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
            """;

    @Test
    void part1Example() {
        var day = new Day09();
        assertEquals("15", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day09();
        assertEquals("465", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var day = new Day09();
        assertEquals("1134", day.part2(example.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day09();
        assertEquals("1269555", day.part2(day.getInput()));
    }

}
