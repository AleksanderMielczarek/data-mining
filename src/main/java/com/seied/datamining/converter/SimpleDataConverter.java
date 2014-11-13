package com.seied.datamining.converter;

import java.util.Arrays;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class SimpleDataConverter implements DataConverter {
    @Override
    public Item convert(String line) {
        line = line.replace("\"", "");
        String[] split = line.split(";");
        return new Item(Integer.parseInt(split[0]), Arrays.asList(Integer.parseInt(split[1])));
    }
}
