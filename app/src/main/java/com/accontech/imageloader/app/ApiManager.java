package com.accontech.imageloader.app;

import com.accontech.imageloader.models.Images;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiManager {

    private final static String END_POINT = "http://54.172.36.94:8080";

    static <T> T getApiClient(final Class<T> clazz) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    private interface ImagesApi{

        @GET("/ttt/test.json")
        Observable<Images> getImages();
    }

    public static void getImages(final Subscriber<Images> subscriber) {
        getApiClient(ImagesApi.class).getImages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
