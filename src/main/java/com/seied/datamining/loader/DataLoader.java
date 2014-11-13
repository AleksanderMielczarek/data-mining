package com.seied.datamining.loader;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.seied.datamining.converter.DataConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class DataLoader {
    private final DataConverter dataConverter;

    public DataLoader(DataConverter dataConverter) {
        this.dataConverter = dataConverter;
    }

    public Multimap<Integer, Integer> load(File file) throws IOException {
        Multimap<Integer, Integer> multimap = ArrayListMultimap.create();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().map(dataConverter::convert).forEach(i -> multimap.putAll(i.getClient(), i.getProducts()));
            return multimap;
        }
    }
}
