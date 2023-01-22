package xyz.azeddine.aoc.days;

import java.util.List;

import xyz.azeddine.aoc.Util;

public class Day6 extends DayBase {

	private final List<String> lines = Util.getInputLines("day6.txt");

	public Day6() {
		System.out.println("[Day6]");
	}

	@Override
	protected void part1() {
		for (String line : lines) {
			NEXT: for (int begin = 0, end = 3; end < line.length(); begin++, end++) {
				CharSequence seq = line.subSequence(begin, end + 1);
				for (char a : seq.toString().toCharArray()) {
					int count = 0;
					for (char b : seq.toString().toCharArray()) {
						if (a == b)
							count++;
					}
					if (count > 1)
						continue NEXT;
				}
				System.out.println("marker appears after " + (end + 1));
				break;
			}
		}
	}

	@Override
	protected void part2() {
		for (String line : lines) {
			NEXT: for (int begin = 0, end = 13; end < line.length(); begin++, end++) {
				CharSequence seq = line.subSequence(begin, end + 1);
				for (char a : seq.toString().toCharArray()) {
					int count = 0;
					for (char b : seq.toString().toCharArray()) {
						if (a == b)
							count++;
					}
					if (count > 1)
						continue NEXT;
				}
				System.out.println("marker appears after " + (end + 1));
				break;
			}
		}
	}
}
