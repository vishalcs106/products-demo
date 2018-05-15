package com.demo.productsdemo.dagger;

import com.demo.productsdemo.room.AppDb;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Vishal on 13-05-2018.
 */
@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {
    Retrofit retrofit();
    AppDb appDb();
}

