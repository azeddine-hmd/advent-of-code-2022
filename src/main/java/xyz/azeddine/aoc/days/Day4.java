package xyz.azeddine.aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import xyz.azeddine.aoc.Util;
import xyz.azeddine.aoc.Util.Pair;

public class Day4 extends DayBase {

	private final List<String> lines = Util.getInputLines("day4.txt");

	public Day4() {
		System.out.println("[Day4]");
	}

	@Override
	protected void part1() {
		int overlaps = 0;
		for (String line : lines) {
			if (line.isBlank())
				continue;
			String[] pairs = line.split(",");
			String[] leftPair = String.valueOf(pairs[0]).split("-");
			String[] rightPair = String.valueOf(pairs[1]).split("-");

			List<Integer> p1 = IntStream.rangeClosed(Integer.parseInt(leftPair[0]), Integer.parseInt(leftPair[1])).boxed().toList();
			List<Integer> p2 = IntStream.rangeClosed(Integer.parseInt(rightPair[0]), Integer.parseInt(rightPair[1])).boxed().toList();
			if (p1.stream().allMatch(p2::contains)) {
				overlaps++;
				continue;
			}
			if (p2.stream().allMatch(p1::contains)) {
				overlaps++;
			}
		}
		System.out.println(overlaps);
	}

	@Override
	protected void part2() {
		int overlaps = 0;
		for (String line : lines) {
			if (line.isBlank())
				continue;
			String[] pairs = line.split(",");
			String[] leftPair = String.valueOf(pairs[0]).split("-");
			String[] rightPair = String.valueOf(pairs[1]).split("-");

			List<Integer> p1 = IntStream.rangeClosed(Integer.parseInt(leftPair[0]), Integer.parseInt(leftPair[1])).boxed().toList();
			List<Integer> p2 = IntStream.rangeClosed(Integer.parseInt(rightPair[0]), Integer.parseInt(rightPair[1])).boxed().toList();
			if (p1.stream().anyMatch(p2::contains)) {
				overlaps++;
			}
		}
		System.out.println(overlaps);
	}

}
