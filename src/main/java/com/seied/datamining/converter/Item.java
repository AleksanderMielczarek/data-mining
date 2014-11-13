package com.seied.datamining.converter;

import java.util.List;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class Item {
    private final int client;
    private final List<Integer> products;

    public Item(int client, List<Integer> products) {
        this.client = client;
        this.products = products;
    }

    public int getClient() {
        return client;
    }

    public List<Integer> getProducts() {
        return products;
    }
}
