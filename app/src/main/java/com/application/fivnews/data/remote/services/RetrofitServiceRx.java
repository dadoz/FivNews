package com.application.fivnews.data.remote.services;


import com.application.fivnews.BuildConfig;
import com.application.fivnews.data.model.News;
import com.application.fivnews.data.model.NewspaperInfo;
import com.application.fivnews.data.remote.services.gson.NewsJsonDeserializer;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceRx {
    /**
     * get service
     * @return
     */
    public NewsService getNewsRetrofit() {
        try {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            return new Retrofit.Builder()
                    .baseUrl(BuildConfig.NEWSAPI_BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .registerTypeAdapter(new TypeToken<List<News>>(){}.getType(), new NewsJsonDeserializer())
                            .create()))
                    .build()
                    .create(NewsService.class);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    /**
     * get service
     * @return
     */
    public NewspaperLookupService getNewspaperLookupRetrofit() {
        try {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new BasicAuthInterceptor(BuildConfig.CLEARBIT_API_USER, ""))
                    .build();

            return new Retrofit.Builder()
                    .baseUrl(BuildConfig.CLEARBIT_LOOKUP_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(NewspaperLookupService.class);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
