package com.seied.datamining.converter;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class Item {
    private final int client;
    private final int product;

    public Item(int client, int product) {
        this.client = client;
        this.product = product;
    }

    public int getClient() {
        return client;
    }

    public int getProduct() {
        return product;
    }
}
