import com.olsson.aoc2021.Day10;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Tests {

    private final String example = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
            """;

    @Test
    void part1Example() {
        var day = new Day10();
        assertEquals("26397", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day10();
        assertEquals("345441", day.part1(day.getInput()));
    }

    @Test
    void part2Example() {
        var day = new Day10();
        assertEquals("288957", day.part2(example.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day10();
        assertEquals("3235371166", day.part2(day.getInput()));
    }

}
