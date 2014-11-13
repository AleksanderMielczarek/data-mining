package com.seied.datamining.collector;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class MultimapCollector implements Collector<String, Multimap<Integer, Integer>, Multimap<Integer, Integer>> {
    private final Multimap<Integer, Integer> data;

    public MultimapCollector() {
        data = ArrayListMultimap.create();
    }

    private Multimap<Integer, Integer> getData() {
        return data;
    }

    @Override
    public Supplier<Multimap<Integer, Integer>> supplier() {
        return this::getData;
    }

    @Override
    public BiConsumer<Multimap<Integer, Integer>, String> accumulator() {
        return (builder, s) -> {
            s = s.replace("\"", "");
            String[] split = s.split(";");
            builder.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        };
    }

    @Override
    public BinaryOperator<Multimap<Integer, Integer>> combiner() {
        return (multimap, multimap2) -> {
            multimap.putAll(multimap2);
            return multimap;
        };
    }

    @Override
    public Function<Multimap<Integer, Integer>, Multimap<Integer, Integer>> finisher() {
        return multimap -> multimap;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.IDENTITY_FINISH);
    }
}
