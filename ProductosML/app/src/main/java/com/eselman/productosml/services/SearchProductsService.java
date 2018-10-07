package com.eselman.productosml.services;

import android.content.Context;
import android.os.AsyncTask;

import com.eselman.productosml.model.Product;
import com.eselman.productosml.products.SearchProductsCallback;
import com.eselman.productosml.products.SearchProductsResponse;
import com.eselman.productosml.rest.SearchProductsRestProvider;
import com.eselman.productosml.utils.RetrofitHelper;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SearchProductsService extends AsyncTask {
    private Context context;
    private String query;
    private SearchProductsCallback searchProductsCallback;
    private List<Product> products;

    public SearchProductsService(Context context, String query, SearchProductsCallback searchProductsCallback) {
        this.context = context;
        this.query = query;
        this.searchProductsCallback = searchProductsCallback;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            RetrofitHelper retrofitHelper = new RetrofitHelper(context);
            SearchProductsRestProvider searchProductsRestProvider = retrofitHelper.createProvider(SearchProductsRestProvider.class);
            Call<SearchProductsResponse> call = searchProductsRestProvider.searchProducts(query);
            Response<SearchProductsResponse> response = call.execute();
            products = response.body().getResults();
        } catch (IOException e) {
            //TODO: Manage error.
            e.printStackTrace();
            products = null;
        }
        return products;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (products != null) {
            searchProductsCallback.onSuccess(products);
        } else {
            searchProductsCallback.onFailure(new Exception("Hubo un error al cargar los productos"));
        }
    }
}
