package com.seied.datamining.converter;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class SimpleDataConverter implements DataConverter {
    @Override
    public Item convert(String line) {
        line = line.replace("\"", "");
        String[] split = line.split(";");
        return new Item(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}
