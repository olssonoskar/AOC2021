package com.olsson.aoc2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day21 implements Solution {

    List<Integer> quanticDieResults = generateQuanticDieResults();

    @Override
    public String part1(List<String> input) {
        var die = new DeterministicDie();
        var player1 = new Player(Integer.parseInt(String.valueOf(input.get(0).charAt(input.get(0).length() - 1))));
        var player2 = new Player(Integer.parseInt(String.valueOf(input.get(1).charAt(input.get(1).length() - 1))));
        boolean player1Turn = true;
        while(player1.score < 1000 && player2.score < 1000) {
            if (player1Turn) {
                player1.roll(die);
            } else {
                player2.roll(die);
            }
            player1Turn = !player1Turn;
        }
        var loser = player1.score >= 1000 ? player2 : player1;
        return String.valueOf(loser.score * die.totalRolls);
    }

    @Override
    public String part2(List<String> input) {
        var player1 = new PlayerStats(1, Integer.parseInt(String.valueOf(input.get(0).charAt(input.get(0).length() - 1))), 0);
        var player2 = new PlayerStats(2, Integer.parseInt(String.valueOf(input.get(1).charAt(input.get(1).length() - 1))), 0);
        var cache = new HashMap<String, Wins>();
        var standings = nextPlayer(player1, player2, cache);
        return String.valueOf(Math.min(standings.player1, standings.player2));
    }

    private Wins playDimension(PlayerStats playerTurn, PlayerStats other, int roll, Map<String, Wins> cache) {
        var takeTurn = playerTurn.updateWith(roll);
        if (takeTurn.score >= 21) {
            if (takeTurn.player == 1) {
                return new Wins(1, 0);
            }
            return new Wins(0, 1);
        }
        var key = cacheKey(other, takeTurn);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        return nextPlayer(other, takeTurn, cache);
    }

    private Wins nextPlayer(PlayerStats next, PlayerStats other, Map<String, Wins> cache) {
        Wins standings = new Wins(0, 0);
        for(int nextRoll: quanticDieResults) {
            standings = standings.combine(playDimension(next, other, nextRoll, cache));
        }
        cache.put(cacheKey(next, other), standings);
        return standings;
    }

    private String cacheKey(PlayerStats first, PlayerStats other) {
        return first.player + "-" + first.pos + first.score + "-" + other.pos + other.score;
    }

    private static class Player {

        int score = 0;
        int pos;

        Player(int pos) {
            this.pos = pos;
        }

        private void roll(DeterministicDie die) {
            var moves = die.roll();
            pos = (this.pos + moves) % 10;
            if(pos == 0) {
                score += 10;
            } else {
                score += pos;
            }
        }
    }
    private record PlayerStats(int player, int pos, int score){
        private PlayerStats updateWith(int roll) {
            var nextPos = (pos + roll) % 10;
            int newScore;
            if(nextPos == 0) {
                newScore = score + 10;
            } else {
                newScore = score + pos;
            }
            return new PlayerStats(player, nextPos, newScore);
        }
    }

    private static class DeterministicDie {
        int lastRoll = 0;
        int totalRolls = 0;

        private int rollOnce() {
            if (lastRoll == 100) {
                lastRoll = 1;
                totalRolls+= 1;
                return 1;
            }
            lastRoll += 1;
            totalRolls += 1;
            return lastRoll;
        }

        public int roll() {
            return rollOnce() + rollOnce() + rollOnce();
        }
    }

    private record Wins(long player1, long player2) {
        Wins combine(Wins other) {
            return new Wins(player1 + other.player1, player2 + other.player2);
        }
    }

    List<Integer> generateQuanticDieResults() {
        return Stream.of(1, 2, 3)
                .flatMap(i -> Stream.of(i + 1, i + 2, i + 3))
                .flatMap(i -> Stream.of(i + 1, i + 2, i + 3))
                .toList();
    }

    @Override
    public String getDay() {
        return "21";
    }
}
