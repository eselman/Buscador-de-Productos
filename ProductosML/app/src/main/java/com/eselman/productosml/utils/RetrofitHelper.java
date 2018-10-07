package com.eselman.productosml.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static final String SERVER_BASE_URL = "https://api.mercadolibre.com/";

    private OkHttpClient.Builder httpClient;

    private Retrofit.Builder builder;

    private Retrofit retrofit;

    private Context context;

    public RetrofitHelper(Context context) {
        this.context = context;
        this.httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder()
                .create();
        builder = new Retrofit.Builder()
                .baseUrl(SERVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    public <S> S createProvider(Class<S> serviceClass) {
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
