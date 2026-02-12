package com.example.wordfreq;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Analyzes text and produces word frequency statistics.
 */
public class WordFrequencyAnalyzer {

    /**
     * Counts the frequency of each word in the given text.
     * Words are lowercased and punctuation is stripped.
     *
     * @param text the input text to analyze
     * @return a map of word to frequency, sorted by frequency descending
     */
    public Map<String, Long> analyze(String text) {
        if (text == null || text.isBlank()) {
            return Map.of();
        }

        return Arrays.stream(text.toLowerCase().split("\\s+"))
                .map(word -> word.replaceAll("[^a-z0-9]", ""))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    /**
     * Returns only words that appear at least {@code minCount} times.
     */
    public Map<String, Long> analyzeWithThreshold(String text, long minCount) {
        return analyze(text).entrySet().stream()
                .filter(e -> e.getValue() >= minCount)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
