package com.eselman.productosml.products;

import com.eselman.productosml.BaseView;
import com.eselman.productosml.model.Product;

import java.util.List;

public interface ProductsContract {
    interface ProductsView extends BaseView<ProductsPresenter> {
        void showProducts(List<Product> products);
        void showErrorMessage(String message);
    }

    interface ProductsPresenter {
        void searchProducts();
    }
}
