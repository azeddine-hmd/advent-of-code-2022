package xyz.azeddine.aoc.days;

public abstract class DayBase {
    public void run() {
        System.out.println("part1:");
        part1();
        System.out.println("part2:");
        part2();
    }

    protected abstract void part1();
    protected abstract void part2();
}
