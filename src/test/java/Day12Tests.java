import com.olsson.aoc2021.Day12;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Tests {

    @Test
    void part1Example1() {
        var day = new Day12();
        assertEquals("10", day.part1(example1.lines().toList()));
    }

    @Test
    void part1Example2() {
        var day = new Day12();
        assertEquals("19", day.part1(example2.lines().toList()));
    }

    @Test
    void part1Example3() {
        var day = new Day12();
        assertEquals("226", day.part1(example3.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day12();
        assertEquals("3887", day.part1(day.getInput()));
    }

    @Test
    void part2Example1() {
        var day = new Day12();
        assertEquals("36", day.part2(example1.lines().toList()));
    }

    @Test
    void part2Example2() {
        var day = new Day12();
        assertEquals("103", day.part2(example2.lines().toList()));
    }

    @Test
    void part2Example3() {
        var day = new Day12();
        assertEquals("3509", day.part2(example3.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day12();
        assertEquals("104834", day.part2(day.getInput()));
    }

    String example1 = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
            """;

    String example2 = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
            """;

    String example3 = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
            """;
}
