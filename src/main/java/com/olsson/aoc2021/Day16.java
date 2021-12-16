package com.olsson.aoc2021;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16 implements Solution {

    public static final int HEADER_LENGTH = 6;
    public static final int LENGTH_TYPE_HEADER = HEADER_LENGTH + 1;
    public static final int LENGTH_HEADER_LENGTH = 22;
    public static final int AMOUNT_HEADER_LENGTH = 18;

    @Override
    public String part1(List<String> input) {
        var bits = new BigInteger(input.get(0), 16).toString(2);
        var expectedLength = 4 * input.get(0).length();
        bits = "0".repeat(expectedLength - bits.length()) + bits;
        var packets = parsePackets(bits);
        return String.valueOf(packets.packets.stream()
                .map(packet -> packet.version)
                .mapToInt(i -> i)
                .sum());
    }

    @Override
    public String part2(List<String> input) {
        return null;
    }

    private Result parsePackets(String bits) {
        var packets = new ArrayList<Packet>();
        if (isZeroTrail(bits)) {
            return new Result(packets, 0);
        }
        var version = parseBits(bits.substring(0, 3));
        var type = parseBits(bits.substring(3, HEADER_LENGTH));
        if (type == 4) {
            var parsed = parseValue(bits.substring(HEADER_LENGTH));
            var packet = new Packet(version, type, -1, 0, parsed.value, "");
            return new Result(List.of(packet), HEADER_LENGTH + parsed.parsedBits());
        }
        var lengthType = parseBits(bits.substring(HEADER_LENGTH, HEADER_LENGTH + 1));
        var parsedBits = 0;
        if (lengthType == 1) {
            var nrOfSubPackets = parseBits(bits.substring(LENGTH_TYPE_HEADER + 1, AMOUNT_HEADER_LENGTH));
            var packet = new Packet(version, type, lengthType, nrOfSubPackets, "", bits.substring(18));
            packets.add(packet);
            var result = parsePackets(bits.substring(AMOUNT_HEADER_LENGTH), nrOfSubPackets);
            packets.addAll(result.packets);
            parsedBits = AMOUNT_HEADER_LENGTH + result.parsedBits();
        } else {
            var packetLength = parseBits(bits.substring(LENGTH_TYPE_HEADER + 1, LENGTH_HEADER_LENGTH));
            var packet = new Packet(version, type, lengthType, packetLength, "", bits.substring(LENGTH_HEADER_LENGTH, LENGTH_HEADER_LENGTH + packetLength));
            packets.add(packet);
            var result = parseLength(getDataFromType0(bits, packetLength));
            packets.addAll(result.packets);
            parsedBits = LENGTH_HEADER_LENGTH + result.parsedBits();
        }

        return new Result(packets, parsedBits);
    }

    private Result parseLength(String bits) {
        int parseAtIndex = 0;
        var packets = new ArrayList<Packet>();
        while(parseAtIndex < bits.length() && !isZeroTrail(bits.substring(parseAtIndex))) {
            var result = parsePackets(bits.substring(parseAtIndex));
            packets.addAll(result.packets);
            parseAtIndex += result.parsedBits;
        }
        return new Result(packets, bits.length());
    }

    private Result parsePackets(String bits, int numberOfPackets) {
        var parseAtIndex = 0;
        var allPackets = new ArrayList<Packet>();
        for (int i = 0; i < numberOfPackets; i++) {
            var packets = parsePackets(bits.substring(parseAtIndex));
            parseAtIndex += packets.parsedBits;
            allPackets.addAll(packets.packets);
        }
        return new Result(allPackets, parseAtIndex);
    }

    private Value parseValue(String bits) {
        int i = 0;
        var patternLength = 5;
        var builder = new StringBuilder();
        while (bits.charAt(i) != '0') {
            builder.append(bits, i + 1, i + patternLength);
            i += 5;
        }
        builder.append(bits, i + 1, i + patternLength);
        return new Value(builder.toString(), i + 5);
    }

    private String getDataFromType0(String bits, int length) {
        return bits.substring(LENGTH_HEADER_LENGTH, LENGTH_HEADER_LENGTH + length);
    }

    private boolean isZeroTrail(String bits) {
        return !bits.contains("1");
    }

    private int parseBits(String bits) {
        return Integer.parseInt(bits, 2);
    }

    private record Packet(int version, int type, int length, int data, String bitValue, String subPackets) {}
    private record Result(List<Packet> packets, int parsedBits){}
    private record Value(String value, int parsedBits) {}

    @Override
    public String getDay() {
        return "16";
    }
}
