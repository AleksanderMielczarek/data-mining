package com.seied.datamining.collector;

import com.google.common.collect.Multimap;

import java.util.function.BiConsumer;

/**
 * Created by Aleksander on 2014-11-14.
 */
public class MultimapConverter implements BiConsumer<Multimap<Integer, Integer>, String> {
    @Override
    public void accept(Multimap<Integer, Integer> multimap, String s) {
        s = s.replace("\"", "");
        String[] split = s.split(";");
        multimap.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
}
