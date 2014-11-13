package com.seied.datamining.miner;

import com.google.common.collect.Multimap;

/**
 * Created by Aleksander on 2014-11-13.
 */
public interface DataMiner {
    public Result mine(Multimap<Integer, Integer> data);
}
