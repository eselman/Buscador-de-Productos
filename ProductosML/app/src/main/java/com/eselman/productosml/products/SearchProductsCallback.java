package com.eselman.productosml.products;

import com.eselman.productosml.model.Product;

import java.util.List;

public interface SearchProductsCallback {
    void onSuccess(List<Product> products);
    void onFailure(Exception e);
}
