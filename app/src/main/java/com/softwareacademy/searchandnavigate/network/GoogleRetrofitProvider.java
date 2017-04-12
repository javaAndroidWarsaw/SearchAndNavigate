package com.softwareacademy.searchandnavigate.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class GoogleRetrofitProvider {

    private String baseUrl;

    public GoogleRetrofitProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Retrofit provideRetrofit() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient;


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        okHttpClient = httpClientBuilder
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Gson gson1 = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd HH:mm:ssZ").create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson1))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
