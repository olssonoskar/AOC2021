import com.olsson.aoc2021.Day07;
import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Tests {

    private final String example = "16,1,2,0,4,2,7,1,2,14";
    private final InputParser parser = new InputParser();

    @Test
    void part1Example() {
        var day = new Day07();
        assertEquals("37", day.part1(Arrays.stream(example.split(",")).toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day07();
        assertEquals("352997", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var day = new Day07();
        assertEquals("168", day.part2(Arrays.stream(example.split(",")).toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day07();
        assertEquals("101571302", day.part2(day.getInput()));
    }

}
