package com.imaginea.training.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Count the word frequency in text file
 *
 * @author sudheerp
 */
public class WordCounter {

    private static Logger LOGGER= LoggerFactory.getLogger(WordCounter.class);
    private static String PATH = "wiki.txt";


    public static void main(String[] args) {

        String data = "";
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(PATH), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            LOGGER.error("Error while reading the file");
        }
        data = contentBuilder.toString();
        Stream<String> stream = Stream.of(data.toLowerCase().split("\\W+")).parallel();

        Map<String, Long> wordFreq = stream
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));
        LOGGER.info("The word counts are -{}",wordFreq);

    }
}
