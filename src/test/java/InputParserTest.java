import com.olsson.aoc2021.InputParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {

    @Test
    void testLines() {
        var parser = new InputParser();
        assertEquals(List.of("1", "2"), parser.lines("Test1"));
    }

    @Test
    void testSplitLines() {
        var parser = new InputParser();
        var input = parser.splitLines("Test2", ",");
        assertEquals("1", input.get(0)[0]);
        assertEquals("1", input.get(0)[1]);
        assertEquals("2", input.get(1)[0]);
        assertEquals("2", input.get(1)[1]);
    }

}
