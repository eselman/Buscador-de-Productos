package com.eselman.productosml.rest;

import com.eselman.productosml.products.SearchProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchProductsRestProvider {
    @GET("/sites/MLU/search")
    Call<SearchProductsResponse> searchProducts(@Query("q") String query);
}
