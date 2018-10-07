package com.eselman.productosml.products;

import android.content.Context;

import com.eselman.productosml.model.Product;
import com.eselman.productosml.services.SearchProductsService;

import java.util.List;

public class ProductsPresenter implements ProductsContract.ProductsPresenter, SearchProductsCallback {
    private SearchProductsService searchProductsService;
    private Context context;
    private String query;
    private ProductsContract.ProductsView view;

    public ProductsPresenter(Context context, String query, ProductsContract.ProductsView view) {
        this.context = context;
        this.query = query;
        this.searchProductsService = new SearchProductsService(context, query,this);
        this.view = view;
    }

    @Override
    public void searchProducts() {
        view.showProgressBar();
        searchProductsService.execute();
    }

    @Override
    public void onSuccess(List<Product> products) {
        view.showProducts(products);
    }

    @Override
    public void onFailure(Exception e) {
        view.showErrorMessage(e.getMessage());
    }
}
