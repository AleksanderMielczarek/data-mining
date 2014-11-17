package com.seied.datamining.miner;

import com.google.common.collect.Multimap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class AprioriDataMiner {
    private final int supportThreshold;
    private final int upperLimit;
    private final ArrayList<List<Integer>> productsChoice;
    private Set<Integer> allProducts;

    public AprioriDataMiner(int supportThreshold, int upperLimit, Multimap<Integer, Integer> data) {
        this.supportThreshold = supportThreshold;
        this.upperLimit = upperLimit;
        this.allProducts = getAllProducts(data);
        this.productsChoice = getProductChoice(data);
    }

    public Result mine() {
        Set<ArrayList<Integer>> productsCombination = getFirstCombinationList();
        Result result = processData(productsCombination);
        return result;
    }

    private Set<ArrayList<Integer>> getFirstCombinationList() {
        Set<ArrayList<Integer>> firstCombinationList = new HashSet<>();
        allProducts.forEach(product -> {
            ArrayList<Integer> internalArrayList = new ArrayList<>(1);
            internalArrayList.add(product);
            firstCombinationList.add(internalArrayList);
        });
        return firstCombinationList;
    }

    private Result processData(Set<ArrayList<Integer>> productsCombination) {
        HashMap<ArrayList<Integer>, Integer> productsOccurences;
        Set<ArrayList<Integer>> productsToCombinationList;
        productsOccurences = checkProductsOccurences(productsCombination);

        for (int i=1;i<this.upperLimit;i++) {
            deleteWrongProducts(productsOccurences);
            productsToCombinationList = getProductsToCombinationList(productsOccurences);
            productsCombination = createCombinations(productsToCombinationList);
            productsOccurences = checkProductsOccurences(productsCombination);
        }

        return new Result(productsOccurences);
    }

    private HashMap<ArrayList<Integer>, Integer> checkProductsOccurences(Set<ArrayList<Integer>> allProductsCombination) {
        HashMap<ArrayList<Integer>, Integer> resultProductOccurences = new HashMap<>();
        for(ArrayList<Integer> productsCombination:allProductsCombination) {
            int occurences = 0;
            for(List singleProductsChoice: productsChoice) {
                if(singleProductsChoice.containsAll(productsCombination))
                    occurences++;
            }
            if(occurences >= this.supportThreshold)
                resultProductOccurences.put(productsCombination, occurences);
        }

        return resultProductOccurences;
    }

    private void deleteWrongProducts(HashMap<ArrayList<Integer>, Integer> productsOccurences) {
        ArrayList<Integer> goodProducts = new ArrayList<>();
        productsOccurences.keySet().forEach(key -> key.forEach(goodProducts::add));
        allProducts = allProducts.stream()
                .filter(goodProducts::contains)
                .collect(Collectors.toCollection(HashSet<Integer>::new));
    }

    private Set<ArrayList<Integer>> getProductsToCombinationList(HashMap<ArrayList<Integer>, Integer> productsOccurences) {
        return productsOccurences.keySet();
    }

    private Set<ArrayList<Integer>> createCombinations(Set<ArrayList<Integer>> productsToCombinationList) {
        Set<ArrayList<Integer>> productsCombination = new HashSet<>();
        productsToCombinationList.forEach(productToComb -> {
            for (int product:allProducts) {
                ArrayList<Integer> productsCombinationElement = new ArrayList<>();
                productsCombinationElement.addAll(productToComb);
                if(!productsCombinationElement.contains(product)) {
                    productsCombinationElement.add(product);
                    productsCombinationElement = productsCombinationElement
                            .stream()
                            .sorted()
                            .collect(Collectors.toCollection(ArrayList<Integer>::new));
                    productsCombination.add(productsCombinationElement);
                }
            }
        });
        return productsCombination;
    }

    private Set<Integer> getAllProducts(Multimap<Integer, Integer> data) {
        Set<Integer> allProducts = new HashSet<>();
        data.values().forEach(allProducts::add);
        return allProducts;
    }

    private ArrayList<List<Integer>> getProductChoice(Multimap<Integer, Integer> data) {
        ArrayList<List<Integer>> productChoice = new ArrayList<>();
        for(Integer key:data.keySet()) {
            productChoice.add((List<Integer>) data.get(key));
        }
        return productChoice;
    }

}
