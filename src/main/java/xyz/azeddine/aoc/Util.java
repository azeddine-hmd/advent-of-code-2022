package xyz.azeddine.aoc;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    public static List<String> getInputLines(String inputFilename) {
        URL inputURL = Util.class.getClassLoader().getResource(inputFilename);
        try (Stream<String> linesStream = Files.lines(Paths.get(inputURL.toURI()))) {
            return linesStream.collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public static class Pair<T, R> {
        private T first;
        private R second;

        public Pair(T first, R second) {
            this.first = first;
            this.second = second;
        }

		public T getFirst() {
			return first;
		}

		public R getSecond() {
			return second;
		}

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static boolean isNumber(String s) {
        return s.matches("[+-]?\\d+");
    }
}
