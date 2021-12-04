package com.olsson.aoc2021;

import java.util.*;

public class Day04 implements Solution {

    private static final int BOARD_LENGTH = 5;
    private static final int ROW_SKIP = 1;


    /**
     * Find the first board to win and return score (unchecked sum times drawn when bingo)
     */
    @Override
    public String part1(List<String> input) {
        var drawn = Arrays.stream(input.get(0).split(","))
                .filter(s -> !s.isBlank())
                .toList();
        var boards = extractBoards(input);
        for (String number : drawn) {
            for (Map<String, Square> board : boards) {
                if (markAndCheck(board, number)) {
                    return String.valueOf(countScore(board) * Integer.parseInt(number));
                }
            }
        }
        return "No board was solved";
    }

    /**
     * Find the last board to win and return score (unchecked sum times drawn when bingo)
     * */
    @Override
    public String part2(List<String> input) {
        var solvedBoards = new HashSet<Integer>();
        var drawn = Arrays.stream(input.get(0).split(","))
                .filter(s -> !s.isBlank())
                .toList();
        var boards = extractBoards(input);
        for (String number : drawn) {
            for (var board = 0; board < boards.size(); board++) {
                if (solvedBoards.contains(board)) {
                    continue;
                }
                var currentBoard = boards.get(board);
                if (markAndCheck(boards.get(board), number)) {
                    if (solvedBoards.size() == boards.size() - 1) {
                        return String.valueOf(countScore(currentBoard) * Integer.parseInt(number));
                    } else {
                        solvedBoards.add(board);
                    }
                }
            }
        }
        return "Final board was not solved";
    }

    private List<Map<String, Square>> extractBoards(List<String> input) {
        var boards = new ArrayList<Map<String, Square>>();
        for (int i = 2; i + BOARD_LENGTH <= input.size(); i += BOARD_LENGTH + ROW_SKIP) {
            boards.add(createBoard(input.subList(i, i + BOARD_LENGTH)));
        }
        return boards;
    }

    private boolean markAndCheck(Map<String, Square> board, String numberDrawn) {
        var square = board.computeIfPresent(numberDrawn,
                (key, value) -> value = new Square(value.number, true, value.row, value.column));
        if(square == null) {
            return false;
        }
        return checkIfSolved(board, square);
    }

    private boolean checkIfSolved(Map<String, Square> board, Square updatedSquare) {
        var checkedInRow = board.values().stream()
                .filter(square -> square.row == updatedSquare.row)
                .filter(Square::checked)
                .count() == BOARD_LENGTH;
        var checkedInColumn = board.values().stream()
                .filter(square -> square.column == updatedSquare.column)
                .filter(Square::checked)
                .count() == BOARD_LENGTH;
        return checkedInRow || checkedInColumn;
    }

    private Map<String, Square> createBoard(List<String> boardInput) {
        var board = new HashMap<String, Square>();
        for (int row = 0; row < boardInput.size(); row++) {
            var elements = Arrays.stream(boardInput.get(row).split(" ")).filter(s -> !s.isBlank()).toList();
            for (int column = 0; column < elements.size(); column++) {
                board.put(elements.get(column), new Square(Integer.parseInt(elements.get(column)), false, row, column));
            }
        }
        return board;
    }

    private int countScore(Map<String, Square> board) {
        return board.values().stream()
                .filter(square -> !square.checked)
                .map(square -> square.number)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private record Square(int number, boolean checked, int row, int column) {}

    @Override
    public String getDay() {
        return "04";
    }
}
