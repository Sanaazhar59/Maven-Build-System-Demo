package com.example.wordfreq;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvExporterTest {

    private final CsvExporter exporter = new CsvExporter();

    @Test
    void exportShouldProduceCsvWithHeaders() throws IOException {
        Map<String, Long> frequencies = new LinkedHashMap<>();
        frequencies.put("hello", 3L);
        frequencies.put("world", 1L);

        String csv = exporter.export(frequencies);
        String[] lines = csv.split("\r?\n");

        assertEquals("word,count", lines[0]);
        assertEquals("hello,3", lines[1]);
        assertEquals("world,1", lines[2]);
        assertEquals(3, lines.length);
    }

    @Test
    void exportShouldHandleEmptyMap() throws IOException {
        String csv = exporter.export(Map.of());
        assertEquals("word,count", csv);
    }
}
