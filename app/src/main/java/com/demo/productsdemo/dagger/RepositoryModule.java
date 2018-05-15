package com.demo.productsdemo.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.demo.productsdemo.App;
import com.demo.productsdemo.room.AppDb;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vishal on 13-05-2018.
 */
@Module
public class RepositoryModule {
    private String baseUrl;
    private Context context;
    public RepositoryModule(String baseUrl, Context context){
        this.baseUrl = baseUrl;
        this.context = context;
    }

    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .baseUrl(baseUrl).build();
    }

    @Provides
    @Singleton
    AppDb providesAppDb(){
        return Room.databaseBuilder(context, AppDb.class,
                "app_db").build();
    }
}

