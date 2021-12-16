package com.olsson.aoc2021;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day16 implements Solution {

    public static final int HEADER_LENGTH = 6;
    public static final int LENGTH_TYPE_HEADER = HEADER_LENGTH + 1;
    public static final int LENGTH_HEADER_LENGTH = 22;
    public static final int AMOUNT_HEADER_LENGTH = 18;

    @Override
    public String part1(List<String> input) {
        var result = parseInput(input.get(0));
        return sumVersions(result.packets).toString();
    }

    @Override
    public String part2(List<String> input) {
        var packets = parseInput(input.get(0));
        return String.valueOf(Long.parseLong(evaluateOperations(packets.packets.get(0)), 2));
    }

    private Long sumVersions(List<Packet> packets) {
        return packets.stream()
                .map(p -> p.version() + sumVersions(p.subPackets()))
                .mapToLong(i -> i)
                .sum();
    }

    private Result parseInput(String input) {
        var bits = new BigInteger(input, 16).toString(2);
        var expectedLength = 4 * input.length();    // Pad lost 0s
        bits = "0".repeat(expectedLength - bits.length()) + bits;
        return parsePackets(bits);
    }

    /**
     * Solve the packets by evaluating the subpackets with the operation
     * If no subpackets, we should be type 4 and return value only (basecase for recursion
     * Once we only have values, perform operation of this type on them
     */
    private String evaluateOperations(Packet packet) {
        if(packet.subPackets.isEmpty()) {
            return packet.bitValue;
        }
        var values = packet.subPackets.stream()
                .map(this::evaluateOperations)
                .toList();
        return Long.toBinaryString(doOperation((int)(packet.type), values));
    }

    private Long doOperation(int type, List<String> bitValues) {
        var fallback = parseBits(bitValues.get(0));
        return switch (type) {
            case 0 -> bitValues.stream().map(this::parseBits).mapToLong(i -> i).sum();
            case 1 -> bitValues.stream().map(this::parseBits).mapToLong(i -> i).reduce((left, right) -> left * right).orElse(fallback);
            case 2 -> bitValues.stream().map(this::parseBits).mapToLong(i -> i).min().orElse(fallback);
            case 3 -> bitValues.stream().map(this::parseBits).mapToLong(i -> i).max().orElse(fallback);
            case 5 -> parseBits(bitValues.get(0)) > parseBits(bitValues.get(1)) ? 1L : 0L;
            case 6 -> parseBits(bitValues.get(0)) < parseBits(bitValues.get(1)) ? 1L : 0L;
            case 7 -> bitValues.get(0).equalsIgnoreCase(bitValues.get(1)) ? 1L : 0L;
            default -> throw new IllegalArgumentException("Failure");
        };
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
            var packet = new Packet(version, type, -1, 0, parsed.value, Collections.emptyList());
            return new Result(List.of(packet), HEADER_LENGTH + parsed.parsedBits());
        }
        var lengthType = parseBits(bits.substring(HEADER_LENGTH, HEADER_LENGTH + 1));
        var parsedBits = 0;
        if (lengthType == 1) {
            var nrOfSubPackets = parseBits(bits.substring(LENGTH_TYPE_HEADER + 1, AMOUNT_HEADER_LENGTH));
            var result = parsePackets(bits.substring(AMOUNT_HEADER_LENGTH), nrOfSubPackets);
            var packet = new Packet(version, type, lengthType, nrOfSubPackets, "", result.packets);
            packets.add(packet);
            parsedBits = AMOUNT_HEADER_LENGTH + result.parsedBits();
        } else {
            var packetLength = parseBits(bits.substring(LENGTH_TYPE_HEADER + 1, LENGTH_HEADER_LENGTH));
            var result = parseLength(getDataFromType0(bits, packetLength));
            var packet = new Packet(version, type, lengthType, packetLength, "", result.packets);
            packets.add(packet);
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

    private Result parsePackets(String bits, long numberOfPackets) {
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

    private String getDataFromType0(String bits, long length) {
        return bits.substring(LENGTH_HEADER_LENGTH, (int) (LENGTH_HEADER_LENGTH + length));
    }

    private boolean isZeroTrail(String bits) {
        return !bits.contains("1");
    }

    private long parseBits(String bits) {
        return Long.parseLong(bits, 2);
    }

    private record Packet(long version, long type, long length, long data, String bitValue, List<Packet> subPackets) {}
    private record Result(List<Packet> packets, int parsedBits){}
    private record Value(String value, int parsedBits) {}

    @Override
    public String getDay() {
        return "16";
    }
}
