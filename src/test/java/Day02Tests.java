import com.olsson.aoc2021.Day02;
import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Part1 - Multiply horizontal and vertical pos after following submarine movement commands
 * Part2 - Same as 1, but up / down commands aligns aim, forwards changes both vertical/depth
 */
class Day02Tests {

    private final InputParser parser = new InputParser();

    @Test
    void part1Example() {
        var commands = List.of("forward 5",
                "down 5",
                "forward 8",
                "up 3",
                "down 8",
                "forward 2");
        assertEquals("150", new Day02().part1(commands));
    }

    @Test
    void part1Solution() {
        var input = parser.lines("02");
        assertEquals("1670340", new Day02().part1(input));
    }

    @Test
    void part2Example() {
        var commands = List.of("forward 5",
                "down 5",
                "forward 8",
                "up 3",
                "down 8",
                "forward 2");
        assertEquals("900", new Day02().part2(commands));
    }

    @Test
    void part2Solution() {
        var input = parser.lines("02");
        assertEquals("1954293920", new Day02().part2(input));
    }



}
