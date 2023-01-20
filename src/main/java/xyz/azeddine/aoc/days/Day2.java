package xyz.azeddine.aoc.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import xyz.azeddine.aoc.Util;

public class Day2 {

    static class Round {

        private static final int LOSE = 0;
        private static final int DRAW = 3;
        private static final int WIN = 6;

        private static final int ROCK = 1;
        private static final int PAPER = 2;
        private static final int SCISSOR = 3;

        private static int convertSymbolToNumber(char symbol) {
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

    private static final String INPUT_FILENAME = "day2.txt";

    public static void run() throws IOException, URISyntaxException {
        final List<String> lines = Util.getInputLines(INPUT_FILENAME);
        System.out.println("part1:");
        part1(lines);
        System.out.println("part2:");
        part2(lines);
    }

    public static void part1(List<String> lines) {
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

    public static void part2(List<String> lines) {
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
}
