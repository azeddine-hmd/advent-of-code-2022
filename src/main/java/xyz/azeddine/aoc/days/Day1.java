package xyz.azeddine.aoc.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import xyz.azeddine.aoc.Util;

public class Day1 {
    private static final String INPUT_FILENAME = "day1.txt";

    public static void run() throws IOException, URISyntaxException {
        List<String> lines = Util.getInputLines(INPUT_FILENAME);
        System.out.println("part1:");
        part1(lines);
        System.out.println("part2:");
        part2(lines);
    }

    private static void part1(List<String> lines) {
        int calories = 0;
        int highestCalories = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                calories = 0;
                continue;
            } else {
                calories += Integer.parseInt(line);
            }
            if (highestCalories < calories)
                highestCalories = calories;
        }
        System.out.println(highestCalories);
    }

    private static void part2(List<String> lines) {
        List<Integer> calories = new ArrayList<>();
        int calorie = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                calories.add(calorie);
                calorie = 0;
                continue;
            } else {
                calorie += Integer.parseInt(line);
            }
        }
        calories.add(calorie);
        int result = calories.stream()
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println(result);
    }
}
