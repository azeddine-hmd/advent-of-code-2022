package xyz.azeddine.aoc.days;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import xyz.azeddine.aoc.Util;

public class Day5 extends DayBase {

	private final List<String> lines = Util.getInputLines("day5.txt");

	public Day5() {
		System.out.println("[Day5]");
	}

	@Override
	protected void part1() {
		// parse and process cargo input
		Map<Integer, LinkedList<Character>> stacks = new TreeMap<>();
		int i;
		for (i = 0; i < lines.size(); i++) {
			if (lines.get(i).isBlank())
				break;
			int start = 0;
			int foundAt;
			while ((foundAt = lines.get(i).indexOf('[', start)) != -1) {
				String line = lines.get(i);
				start = foundAt + 1;
				int key = foundAt == 0 ? 1 : foundAt / 4 + 1;
				char value = line.charAt(foundAt + 1);
				if (!stacks.containsKey(key))
					stacks.put(key, new LinkedList<>());
				stacks.get(key).add(value);
			}
		}

		// parse and process cargo crane procedures input
		i++;
		for (; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] s = line.split(" ");
			String command = s[0];
			int times = Integer.parseInt(s[1]);
			int from = Integer.parseInt(s[3]);
			int to = Integer.parseInt(s[5]);

			if (command.equals("move")) {
				LinkedList<Character> fromStack = stacks.get(from);
				LinkedList<Character> toStack = stacks.get(to);

				for (int count = 0; count < times; count++)
					toStack.push(fromStack.pop());
			}
		}

		// peek on each stack and print it
		System.out.print("message: ");
		for (var stackSet : stacks.entrySet()) {
			System.out.print(stackSet.getValue().peek());
		}
		System.out.println();
	}


	@Override
	protected void part2() {
		// parse and process cargo input
		Map<Integer, LinkedList<Character>> stacks = new TreeMap<>();
		int i;
		for (i = 0; i < lines.size(); i++) {
			if (lines.get(i).isBlank())
				break;
			int start = 0;
			int foundAt;
			while ((foundAt = lines.get(i).indexOf('[', start)) != -1) {
				String line = lines.get(i);
				start = foundAt + 1;
				int key = foundAt == 0 ? 1 : foundAt / 4 + 1;
				char value = line.charAt(foundAt + 1);
				if (!stacks.containsKey(key))
					stacks.put(key, new LinkedList<>());
				stacks.get(key).add(value);
			}
		}

		// parse and process cargo crane procedures input
		i++;
		for (; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] s = line.split(" ");
			String command = s[0];
			int times = Integer.parseInt(s[1]);
			int from = Integer.parseInt(s[3]);
			int to = Integer.parseInt(s[5]);

			if (command.equals("move")) {
				LinkedList<Character> fromStack = stacks.get(from);
				LinkedList<Character> toStack = stacks.get(to);

				List<Character> crates = new ArrayList<>();
				for (int count = 0; count < times; count++)
					crates.add(fromStack.pop());
				for (int count = 0; count < times; count++)
					toStack.push(crates.remove(crates.size() - 1));
			}
		}

		// peek on each stack and print it
		System.out.print("message: ");
		for (var stackSet : stacks.entrySet()) {
			System.out.print(stackSet.getValue().peek());
		}
		System.out.println();
	}

	private void printStacks(Map<Integer, LinkedList<Character>> stacks) {
		stacks.forEach((k, stack) -> {
			System.out.println("stack = " + k + ":");
			if (!stack.isEmpty()) {
				stack.forEach(System.out::println);
			} else  {
				System.out.println("stack is empty");
			}
		});
	}
}
