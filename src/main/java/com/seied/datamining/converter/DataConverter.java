package com.seied.datamining.converter;

import com.google.common.collect.Multimap;

/**
 * Created by Aleksander on 2014-11-13.
 */
public interface DataConverter {
    public void convert(String line);

    public Multimap<Integer, Integer> buildData();
}
