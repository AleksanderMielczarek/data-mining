package com.seied.datamining.miner;

import com.google.common.collect.Multimap;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class AprioriDataMiner implements DataMiner {
    private final int supportThreshold;
    private final int upperLimit;

    public AprioriDataMiner(int supportThreshold, int upperLimit) {
        this.supportThreshold = supportThreshold;
        this.upperLimit = upperLimit;
    }

    @Override
    public Result mine(Multimap<Integer, Integer> data) {
        Set<Integer> products = new HashSet<>();
        data.values().forEach(products::add);

        Map<Integer, Integer> productOccurrences = new HashMap<>(products.size());
        for (Integer value : products) {
            int occurrences = CollectionUtils.cardinality(value, data.values());
            if (occurrences >= supportThreshold) {
                productOccurrences.put(value, occurrences);
            }
        }

        for (int i = 2; i <= upperLimit; i++) {

        }

        System.out.println(productOccurrences);

        return null;
    }
}
