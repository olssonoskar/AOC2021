package com.olsson.aoc2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputParser {

    public List<String> lines(String day) {
        var path = findPath(day);
        try {
            return Files.readAllLines(path);
        } catch (IOException ex) {
            throw new IllegalArgumentException(path + " - unable to read contents");
        }
    }

    public List<String[]> splitLines(String day, String separator) {
        return lines(day)
                .stream()
                .map(s -> s.split(separator))
                .toList();
    }

    private Path findPath(String day) {
        var fileName = "day" + day + ".txt";
        var url = getClass().getClassLoader().getResource(fileName);
        if (url == null) {
            throw new IllegalArgumentException(fileName + " - file was not found");
        }
        try {
            return Path.of(url.toURI());
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

}
