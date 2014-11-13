package com.seied.datamining.loader;

import com.google.common.collect.Multimap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collector;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class DataLoader {
    private final Collector<String, ?, Multimap<Integer, Integer>> collector;

    public DataLoader(Collector<String, ?, Multimap<Integer, Integer>> collector) {
        this.collector = collector;
    }

    public Multimap<Integer, Integer> load(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().collect(collector);
        }
    }
}
