package com.thompalmer.imgurgallery.data;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thompalmer on 2016-11-30.
 */

public class ImgurServiceProvider {

    private static final String AUTHORIZATION = "Authorization";
    private static final String CLIENT_ID = "Client-ID cfeb50ce919f226";

    public static ImgurService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com")
                .client(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request originalRequest = chain.request();
                                Request authenticatedRequest = originalRequest.newBuilder()
                                        .header(AUTHORIZATION, CLIENT_ID)
                                        .method(originalRequest.method(), originalRequest.body())
                                        .build();

                                return chain.proceed(authenticatedRequest);
                            }
                        }).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ImgurService.class);
    }
}
