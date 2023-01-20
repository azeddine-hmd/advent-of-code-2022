package xyz.azeddine.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    public static List<String> getInputLines(String inputFilename) throws IOException, URISyntaxException {
        URL inputURL = Util.class.getClassLoader().getResource(inputFilename);
        try (Stream<String> linesStream = Files.lines(Paths.get(inputURL.toURI()))) {
            return linesStream.collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }
}
