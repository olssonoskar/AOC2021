import com.olsson.aoc2021.Day17;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Tests {

    @Test
    void part1Solution() {
        var day = new Day17();
        assertEquals("5151", day.part1(List.of("target area: x=135..155, y=-102..-78")));
    }

    @Test
    void part2Example() {
        var day = new Day17();
        assertEquals("112", day.part2(List.of("target area: x=20..30, y=-10..-5")));
    }

    @Test
    void part2Solution() {
        var day = new Day17();
        assertEquals("968", day.part2(List.of("target area: x=135..155, y=-102..-78")));
    }

}
