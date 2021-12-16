import com.olsson.aoc2021.Day16;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Tests {


    @Test
    void part1SingleValue() {
        var day = new Day16();
        assertEquals("6", day.part1(singleValue.lines().toList()));
    }

    @Test
    void part1Length27Packet() {
        var day = new Day16();
        assertEquals("9", day.part1(length27.lines().toList()));
    }

    @Test
    void part1SubPackets3() {
        var day = new Day16();
        assertEquals("14", day.part1(subPackets3.lines().toList()));
    }

    @Test
    void part1Ex1() {
        var day = new Day16();
        assertEquals("16", day.part1(ex1.lines().toList()));
    }

    @Test
    void part1Ex2() {
        var day = new Day16();
        assertEquals("12", day.part1(ex2.lines().toList()));
    }

    @Test
    void part1Ex3() {
        var day = new Day16();
        assertEquals("23", day.part1(ex3.lines().toList()));
    }

    @Test
    void part1Ex4() {
        var day = new Day16();
        assertEquals("31", day.part1(ex4.lines().toList()));
    }

    @Test
    void part1Solution() {
        var day = new Day16();
        assertEquals("991", day.part1(day.getInput()));
    }

    @Test
    void part2Ex1() {
        var day = new Day16();
        assertEquals("3", day.part2(ex21.lines().toList()));
    }

    @Test
    void part2Ex4() {
        var day = new Day16();
        assertEquals("9", day.part2(ex24.lines().toList()));
    }

    @Test
    void part2Ex8() {
        var day = new Day16();
        assertEquals("1", day.part2(ex28.lines().toList()));
    }

    @Test
    void part2Solution() {
        var day = new Day16();
        assertEquals("1264485568252", day.part2(day.getInput()));
    }

    private static final String singleValue = "D2FE28";
    private static final String length27 = "38006F45291200";
    private static final String subPackets3 = "EE00D40C823060";
    private static final String ex1 = "8A004A801A8002F478";
    private static final String ex2 = "620080001611562C8802118E34";
    private static final String ex3 = "C0015000016115A2E0802F182340";
    private static final String ex4 = "A0016C880162017C3686B18A3D4780";

    // Examples for part 2 with order from AOC page
    private static final String ex21 = "C200B40A82";
    private static final String ex24 = "CE00C43D881120";
    private static final String ex28 = "9C0141080250320F1802104A08";
}
