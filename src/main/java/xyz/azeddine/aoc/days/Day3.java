package xyz.azeddine.aoc.days;

import java.util.ArrayList;
import java.util.List;

import xyz.azeddine.aoc.Util;

public class Day3 extends DayBase {

    private final List<String> lines = Util.getInputLines("day3.txt");
    private final String alphabet = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Day3() {
        System.out.println("[Day3]");
    }

    @Override()
    protected void part1() {
        int priority = 0;

        Task1:
        for (String line : lines) {
            for (int i = 0; i < line.length() / 2; i++) {
                for (int j = line.length() / 2; j < line.length(); j++) {
                    if (line.charAt(i) == line.charAt(j)) {
                        priority += alphabet.indexOf(line.charAt(i));
                        continue Task1;
                    }
                }
            }
        }

        System.out.println(priority);
    }

    @Override()
    protected void part2() {
        int priority = 0;
        int count = 0;

        List<String> groups = new ArrayList<>();
        StringBuffer group = new StringBuffer();
        for (String line : lines) {
            count++;
            group.append(line + "\n");
            if (count == 3) {
                String[] bags = group.toString().split("\n");
                TASK2:
                for (int i = 0; i < bags[0].length(); i++) {
                    for (int j = 0; j < bags[1].length(); j++) {
                        if (bags[0].charAt(i) == bags[1].charAt(j)) {
                            for (int k = 0; k < bags[2].length(); k++) {
                                if (bags[1].charAt(j) == bags[2].charAt(k)) {
                                    priority += alphabet.indexOf(bags[2].charAt(k));
                                    break TASK2;
                                }
                            }
                        }
                    }
                }
                count = 0;
                group.setLength(0);
            }        
        }
        System.out.println(priority);
    }
}
