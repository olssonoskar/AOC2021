import com.olsson.aoc2021.Day03;
import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Tests {

    private final InputParser parser = new InputParser();

    @Test
    void part1Example() {
        var input = """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010
                """;
        assertEquals("198", new Day03().part1(input.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day03();
        var input = parser.lines(day.getDay());
        assertEquals("3277364", day.part1(input));
    }

    @Test
    void part2Example() {
        var input = """
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010
                """;
        assertEquals("230", new Day03().part2(input.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day03();
        var input = parser.lines(day.getDay());
        assertEquals("5736383", day.part2(input));
    }
}
