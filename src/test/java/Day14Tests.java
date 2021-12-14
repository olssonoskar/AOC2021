import com.olsson.aoc2021.Day14;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Tests {

    @Test
    void part1Example() {
        var day = new Day14();
        assertEquals("1588", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day14();
        assertEquals("2223", day.part1(day.getInput()));
    }

    @Test
    void part2Solution() {
        var day = new Day14();
        assertEquals("2566282754493", day.part2(day.getInput()));
    }


    String example = """
            NNCB
                        
            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
            """;

}
