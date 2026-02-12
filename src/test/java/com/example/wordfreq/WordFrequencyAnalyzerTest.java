package com.example.wordfreq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordFrequencyAnalyzerTest {

    private WordFrequencyAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new WordFrequencyAnalyzer();
    }

    @Test
    void analyzeShouldCountWordFrequencies() {
        Map<String, Long> result = analyzer.analyze("hello world hello");
        assertEquals(2L, result.get("hello"));
        assertEquals(1L, result.get("world"));
    }

    @Test
    void analyzeShouldBeCaseInsensitive() {
        Map<String, Long> result = analyzer.analyze("Java java JAVA");
        assertEquals(3L, result.get("java"));
        assertEquals(1, result.size());
    }

    @Test
    void analyzeShouldStripPunctuation() {
        Map<String, Long> result = analyzer.analyze("hello, world! hello.");
        assertEquals(2L, result.get("hello"));
        assertEquals(1L, result.get("world"));
    }

    @Test
    void analyzeShouldReturnEmptyMapForNullInput() {
        Map<String, Long> result = analyzer.analyze(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void analyzeShouldReturnEmptyMapForBlankInput() {
        Map<String, Long> result = analyzer.analyze("   ");
        assertTrue(result.isEmpty());
    }

    @Test
    void analyzeShouldSortByFrequencyDescending() {
        Map<String, Long> result = analyzer.analyze("a b b c c c");
        // First entry should be the most frequent
        Map.Entry<String, Long> first = result.entrySet().iterator().next();
        assertEquals("c", first.getKey());
        assertEquals(3L, first.getValue());
    }

    @Test
    void analyzeWithThresholdShouldFilterLowFrequencyWords() {
        Map<String, Long> result = analyzer.analyzeWithThreshold("a b b c c c", 2);
        assertFalse(result.containsKey("a"));  // appears once, below threshold
        assertTrue(result.containsKey("b"));   // appears twice
        assertTrue(result.containsKey("c"));   // appears three times
    }
}
