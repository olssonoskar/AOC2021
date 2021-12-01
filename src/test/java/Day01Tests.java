import com.olsson.aoc2021.Day01;
import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Tests {

    private final InputParser parser = new InputParser();

    @Test
    void part1Example() {
        var input = List.of("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");
        var day1 = new Day01();
        assertEquals("7", day1.part1(input));
    }

    @Test
    void solutionPart1() {
        var input = parser.lines("01");
        var day1 = new Day01();
        assertEquals("1832", day1.part1(input));
    }

    @Test
    void part2Example() {
        var input = List.of("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");
        var day1 = new Day01();
        assertEquals("5", day1.part2(input));
    }


    @Test
    void part2Solution() {
        var input = parser.lines("01");
        var day1 = new Day01();
        assertEquals("1858", day1.part2(input));
    }

}
