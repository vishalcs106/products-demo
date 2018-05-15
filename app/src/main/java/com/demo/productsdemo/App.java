package com.demo.productsdemo;

import android.app.Application;
import android.content.Context;

import com.demo.productsdemo.dagger.ApiComponent;
import com.demo.productsdemo.dagger.DaggerApiComponent;
import com.demo.productsdemo.dagger.DaggerRepositoryComponent;
import com.demo.productsdemo.dagger.RepositoryComponent;
import com.demo.productsdemo.dagger.RepositoryModule;
import com.facebook.stetho.Stetho;

/**
 * Created by Vishal on 13-05-2018.
 */

public class App extends Application{
    private static Context context;
    private static ApiComponent apiComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Stetho.initializeWithDefaults(this);
    }

    public static ApiComponent getApiComponent(){
        if(apiComponent != null)
            return apiComponent;
        else return DaggerApiComponent.builder().repositoryComponent(getRepositoryComponent()).build();
    }

    private static RepositoryComponent getRepositoryComponent() {
        return DaggerRepositoryComponent.builder().repositoryModule(new RepositoryModule(
                BuildConfig.BASE_URL, context)).build();
    }
}
