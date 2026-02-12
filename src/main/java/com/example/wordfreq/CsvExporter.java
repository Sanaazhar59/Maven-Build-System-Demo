package com.example.wordfreq;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * Exports word frequency results to CSV format using Apache Commons CSV.
 * Demonstrates a second compile-scope dependency downloaded from Maven Central.
 */
public class CsvExporter {

    /**
     * Formats word frequencies as a CSV string with headers.
     *
     * @param frequencies map of word to count (order is preserved)
     * @return a CSV-formatted string
     */
    public String export(Map<String, Long> frequencies) throws IOException {
        StringWriter writer = new StringWriter();
        CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                .setHeader("word", "count")
                .build();

        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
            for (Map.Entry<String, Long> entry : frequencies.entrySet()) {
                printer.printRecord(entry.getKey(), entry.getValue());
            }
        }
        return writer.toString().strip();
    }
}
