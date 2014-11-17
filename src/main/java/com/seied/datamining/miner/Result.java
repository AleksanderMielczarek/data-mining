package com.seied.datamining.miner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Janek on 2014-11-17.
 */
public class Result {
    private HashMap<ArrayList<Integer>, Integer> result;

    public Result(HashMap<ArrayList<Integer>, Integer> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
