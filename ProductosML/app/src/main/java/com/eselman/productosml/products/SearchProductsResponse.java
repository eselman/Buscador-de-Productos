package com.eselman.productosml.products;

import com.eselman.productosml.model.Product;

import java.util.List;

public class SearchProductsResponse {
    private List<Product> results;

    public List<Product> getResults() {
        return results;
    }

    public void setResults(List<Product> results) {
        this.results = results;
    }
}
