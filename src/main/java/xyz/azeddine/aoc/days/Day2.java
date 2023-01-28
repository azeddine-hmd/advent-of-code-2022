package xyz.azeddine.aoc.days;

import java.util.ArrayList;
import java.util.List;

import xyz.azeddine.aoc.Util;

public final class Day2 extends DayBase {

    private final List<String> lines = Util.getInputLines("day2.txt");

    public Day2() {
        System.out.println("[Day2]");
    }

    @Override
    protected void part1() {
        final List<Round> rounds = new ArrayList<>();
        for (String line : lines) {
            final String[] result = line.split(" ");
            char first = result[0].charAt(0);
            char second = result[1].charAt(0);
            rounds.add(new Round(first, second));
        }

        // calculating total score of all rounds
        int sum = rounds.stream()
            .map(round -> round.score())
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println(sum);
    }

    @Override
    protected void part2() {
        final List<Round> rounds = new ArrayList<>();
        for (String line : lines) {
            final String[] result = line.split(" ");
            char first = result[0].charAt(0);
            char second = switch (result[1].charAt(0)) {
                // draw
                case 'Y' -> first;
                // lose
                case 'X' -> {
                    if (first == 'A') {
                        yield 'Z';
                    } else if (first == 'B') {
                        yield 'X';
                    } else {
                        yield 'Y';
                    }
                }
                // win
                case 'Z' -> {
                    if (first == 'A') {
                        yield 'Y';
                    } else if (first == 'B') {
                        yield 'Z';
                    } else {
                        yield 'X';
                    }
                }
                default -> throw new IllegalArgumentException();
            };
            rounds.add(new Round(first, second));
        }
        // calculating total score of all rounds
        int sum = rounds.stream()
            .map(round -> round.score())
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println(sum);
    }

    private class Round {

        private final int LOSE = 0;
        private final int DRAW = 3;
        private final int WIN = 6;

        private final int ROCK = 1;
        private final int PAPER = 2;
        private final int SCISSOR = 3;

        private int convertSymbolToNumber(char symbol) {
            return switch (symbol) {
                case 'A', 'X' -> 1;
                case 'B', 'Y' -> 2;
                case 'C', 'Z' -> 3;
                default -> throw new IllegalArgumentException("unknown symbol `" + symbol + "`");
            };
        }

        private int first;
        private int second;

        public Round(char first, char second) {
            this.first = convertSymbolToNumber(first);
            this.second = convertSymbolToNumber(second);
        }

        @Override
        public String toString() {
            return "Round[first: " + this.first + ", second: " + this.second + ", score: " + score() + "]";
        }

        public int score() {
            if (this.first == this.second) {
                return this.second + DRAW;
            } else if (this.second == ROCK && this.first == PAPER
                    || this.second == PAPER && this.first == SCISSOR
                    || this.second == SCISSOR && this.first == ROCK) {
                return this.second + LOSE;
            } else {
                return this.second + WIN;
            }
        }
    }
}
