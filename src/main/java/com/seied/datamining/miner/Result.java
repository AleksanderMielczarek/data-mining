package com.seied.datamining.miner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Janek on 2014-11-17.
 */
public class Result {
    private Map<List<Integer>, Integer> result;

    public Result(Map<List<Integer>, Integer> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        result.forEach((integers, integer) ->
                stringBuilder
                        .append(integers)
                        .append(" = ")
                        .append(integer)
                        .append(System.getProperty("line.separator")));

        return stringBuilder.toString();
    }

}
