package com.seied.datamining.converter;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class SimpleDataConverter implements DataConverter {
    private final Multimap<Integer, Integer> data;

    public SimpleDataConverter() {
        data = ArrayListMultimap.create();
    }

    @Override
    public void convert(String line) {
        line = line.replace("\"", "");
        String[] split = line.split(";");
        data.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    @Override
    public Multimap<Integer, Integer> buildData() {
        return data;
    }
}
