import com.olsson.aoc2021.Day06;
import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Tests {

    private final InputParser parser = new InputParser();

    @Test
    void part1Example() {
        var example = Arrays.stream("3,4,3,1,2".split(",")).toList();
        var day = new Day06();
        assertEquals("26", day.part1(example, 18));
    }

    @Test
    void part1Example2() {
        var example = Arrays.stream("3,4,3,1,2".split(",")).toList();
        var day = new Day06();
        assertEquals("5934", day.part1(example));
    }

    @Test
    void part1Solution() {
        var day = new Day06();
        assertEquals("354564", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var example = Arrays.stream("3,4,3,1,2".split(",")).toList();
        var day = new Day06();
        assertEquals("26984457539", day.part2(example));
    }

    @Test
    void part2Solution() {
        var day = new Day06();
        assertEquals("1609058859115", day.part2(day.getInput()));
    }
}
