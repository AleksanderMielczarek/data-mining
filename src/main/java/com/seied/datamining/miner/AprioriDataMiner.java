package com.seied.datamining.miner;

import com.google.common.collect.Multimap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2014-11-13.
 */
public class AprioriDataMiner implements DataMiner {

    private final int supportThreshold;
    private final int upperLimit;
    private List<List<Integer>> productsChoice;
    private Set<Integer> allProducts;

    public AprioriDataMiner(int supportThreshold, int upperLimit) {
        this.supportThreshold = supportThreshold;
        this.upperLimit = upperLimit;
    }

    @Override
    public Result mine(Multimap<Integer, Integer> data) {
        allProducts = getAllProducts(data);
        productsChoice = getProductChoice(data);
        Set<List<Integer>> productsCombination = getFirstCombinationList();
        Result result = processData(productsCombination);
        return result;
    }

    private Set<List<Integer>> getFirstCombinationList() {
        Set<List<Integer>> firstCombinationList = new HashSet<>();
        allProducts.forEach(product -> {
            List<Integer> internalArrayList = new ArrayList<>(1);
            internalArrayList.add(product);
            firstCombinationList.add(internalArrayList);
        });
        return firstCombinationList;
    }

    private Result processData(Set<List<Integer>> productsCombination) {
        Map<List<Integer>, Integer> productsOccurrences;
        Set<List<Integer>> productsToCombinationList;
        productsOccurrences = checkProductsOccurrences(productsCombination);

        for (int i = 1; i < this.upperLimit; i++) {
            deleteWrongProducts(productsOccurrences);
            productsToCombinationList = getProductsToCombinationList(productsOccurrences);
            productsCombination = createCombinations(productsToCombinationList);
            productsOccurrences = checkProductsOccurrences(productsCombination);
        }

        return new Result(productsOccurrences);
    }

    private Map<List<Integer>, Integer> checkProductsOccurrences(Set<List<Integer>> allProductsCombination) {
        Map<List<Integer>, Integer> resultProductOccurrences = new HashMap<>();
        for (List<Integer> productsCombination : allProductsCombination) {
            int occurrences = 0;
            for (List singleProductsChoice : productsChoice) {
                if (singleProductsChoice.containsAll(productsCombination))
                    occurrences++;
            }
            if (occurrences >= this.supportThreshold)
                resultProductOccurrences.put(productsCombination, occurrences);
        }

        return resultProductOccurrences;
    }

    private void deleteWrongProducts(Map<List<Integer>, Integer> productsOccurrences) {
        List<Integer> goodProducts = new ArrayList<>();
        productsOccurrences.keySet().forEach(key -> key.forEach(goodProducts::add));
        allProducts = allProducts.stream()
                .filter(goodProducts::contains)
                .collect(Collectors.toCollection(HashSet<Integer>::new));
    }

    private Set<List<Integer>> getProductsToCombinationList(Map<List<Integer>, Integer> productsOccurrences) {
        return productsOccurrences.keySet();
    }

    private Set<List<Integer>> createCombinations(Set<List<Integer>> productsToCombinationList) {
        Set<List<Integer>> productsCombination = new HashSet<>();
        productsToCombinationList.forEach(productToComb -> {
            for (int product : allProducts) {
                List<Integer> productsCombinationElement = new ArrayList<>();
                productsCombinationElement.addAll(productToComb);
                if (!productsCombinationElement.contains(product)) {
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

    private List<List<Integer>> getProductChoice(Multimap<Integer, Integer> data) {
        List<List<Integer>> productChoice = data.keySet().stream()
                .map(key -> (List<Integer>) data.get(key))
                .collect(Collectors.toList());

        return productChoice;
    }
}
