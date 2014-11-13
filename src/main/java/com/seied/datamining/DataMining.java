package com.seied.datamining;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Multimap;
import com.seied.datamining.converter.DataConverter;
import com.seied.datamining.converter.SimpleDataConverter;
import com.seied.datamining.loader.DataLoader;
import com.seied.datamining.miner.AprioriDataMiner;
import com.seied.datamining.miner.DataMiner;
import com.seied.datamining.miner.Result;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class DataMining {
    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        File file = new File("D:\\Dropbox\\Studia\\SEIED\\lab3\\zakupy.txt");
        DataConverter dataConverter = new SimpleDataConverter();
        DataLoader dataLoader = new DataLoader(dataConverter);
        Multimap<Integer, Integer> data = dataLoader.load(file);
        DataMiner dataMiner = new AprioriDataMiner(3, 3);
        Result result = dataMiner.mine(data);
        System.out.println("Total time [ms]: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
