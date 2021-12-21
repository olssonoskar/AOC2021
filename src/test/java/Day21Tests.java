import com.olsson.aoc2021.Day21;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Tests {

    @Test
    void part1Example() {
        var day = new Day21();
        assertEquals("739785", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day21();
        assertEquals("739785", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var day = new Day21();
        assertEquals("341960390180808", day.part2(example.lines().toList()));
    }

    String example = """
            Player 1 starting position: 4
            Player 2 starting position: 8
            """;

}
