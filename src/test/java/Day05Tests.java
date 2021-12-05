import com.olsson.aoc2021.Day05;
import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Tests {

    private final static InputParser inputParser = new InputParser();

    @Test
    void part1Example() {
        var day = new Day05();
        assertEquals("5", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day05();
        assertEquals("7436", day.part1(inputParser.lines("05")));
    }

    @Test
    void part2Example() {
        var day = new Day05();
        assertEquals("12", day.part2(example.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day05();
        assertEquals("21104", day.part2(inputParser.lines("05")));
    }

    String example = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
            """;

}
