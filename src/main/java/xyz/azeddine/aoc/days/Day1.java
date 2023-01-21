package xyz.azeddine.aoc.days;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import xyz.azeddine.aoc.Util;

public final class Day1 extends DayBase {

    private final List<String> lines = Util.getInputLines("day1.txt");

    public Day1() {
        System.out.println("[Day1]");
    }

    @Override()
    protected void part1() {
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

    @Override()
    protected void part2() {
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
