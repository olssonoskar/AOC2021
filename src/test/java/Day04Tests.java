import com.olsson.aoc2021.Day04;
import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Tests {

    private final InputParser inputParser = new InputParser();

    @Test
    void part1Example() {
        var day = new Day04();
        assertEquals("4512", day.part1(example.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day04();
        assertEquals("8580", day.part1(inputParser.lines(day.getDay())));
    }

    @Test
    void part2Example() {
        var day = new Day04();
        assertEquals("1924", day.part2(example.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day04();
        assertEquals("9576", day.part2(inputParser.lines(day.getDay())));
    }

    private static final String example = """
            7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
                        
            22 13 17 11  0
             8  2 23  4 24
            21  9 14 16  7
             6 10  3 18  5
             1 12 20 15 19
                        
             3 15  0  2 22
             9 18 13 17  5
            19  8  7 25 23
            20 11 10 24  4
            14 21 16 12  6
                        
            14 21 17 24  4
            10 16 15  9 19
            18  8 23 26 20
            22 11 13  6  5
             2  0 12  3  7
            """;

}
