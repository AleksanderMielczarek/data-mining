package com.seied.datamining;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Multimap;
import com.seied.datamining.collector.MultimapCollector;
import com.seied.datamining.collector.MultimapConverter;
import com.seied.datamining.loader.DataLoader;
import com.seied.datamining.miner.AprioriDataMiner;
import com.seied.datamining.miner.DataMiner;
import com.seied.datamining.miner.Result;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.stream.Collector;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class DataMining {
    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        DataMiningArguments miningArguments = DataMiningArguments.parse(args);

        System.out.println("Loading data...");
        BiConsumer<Multimap<Integer, Integer>, String> converter = new MultimapConverter();
        Collector<String, ?, Multimap<Integer, Integer>> collector = new MultimapCollector(converter);
        DataLoader dataLoader = new DataLoader(collector);
        Multimap<Integer, Integer> data = dataLoader.load(miningArguments.getFile());

        System.out.println("Mining data...");
        DataMiner dataMiner = new AprioriDataMiner(miningArguments.getSupportThreshold(), miningArguments.getProductNr());
        Result result = dataMiner.mine(data);

        System.out.println("Result:");
        System.out.println(result);
        System.out.println("Total time [ms]: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
