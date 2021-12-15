import com.olsson.aoc2021.Day15;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Tests {

    @Test
    void part1Example() {
        var day = new Day15();
        assertEquals("40", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day15();
        assertEquals("361", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var day = new Day15();
        assertEquals("315", day.part2(example.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day15();
        assertEquals("2838", day.part2(day.getInput()));
    }

    private final String example = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
            """;
}
