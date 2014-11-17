package com.seied.datamining;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Multimap;
import com.seied.datamining.collector.MultimapCollector;
import com.seied.datamining.collector.MultimapConverter;
import com.seied.datamining.loader.DataLoader;
import com.seied.datamining.loader.FileLoader;
import com.seied.datamining.miner.AprioriDataMiner;
import com.seied.datamining.miner.Result;

import java.io.File;
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
        FileLoader fileLoader = new FileLoader("zakupy_real_test.txt");
        File file = fileLoader.getFile();
        BiConsumer<Multimap<Integer, Integer>, String> converter = new MultimapConverter();
        Collector<String, ?, Multimap<Integer, Integer>> collector = new MultimapCollector(converter);
        DataLoader dataLoader = new DataLoader(collector);
        Multimap<Integer, Integer> data = dataLoader.load(file);
        int supportThreshold = 7;
        int productNr = 5;
        AprioriDataMiner dataMiner = new AprioriDataMiner(supportThreshold, productNr, data);
        Result result = dataMiner.mine();
        System.out.println(result);
        System.out.println("Total time [ms]: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
