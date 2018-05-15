package com.demo.productsdemo.dagger;

import com.demo.productsdemo.productslisting.GetProductsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Vishal on 13-05-2018.
 */
@Module
public class ApiModule {

    @Provides
    @CustomScope
    GetProductsService providesGetProductsService(Retrofit retrofit) {
        return retrofit.create(GetProductsService.class);
    }
}
