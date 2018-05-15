package com.demo.productsdemo.productdetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.demo.productsdemo.App;
import com.demo.productsdemo.base.ResultsCallback;
import com.demo.productsdemo.room.AppDb;
import com.demo.productsdemo.room.ProductEntity;

import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vishal on 15-05-2018.
 */

public class ProductDetailsViewModel extends AndroidViewModel {
    private int productId;

    @Inject
    AppDb appDb;

    public ProductDetailsViewModel(@NonNull Application application) {
        super(application);
        resolveDependencies();
    }

    private void resolveDependencies() {
        App.getApiComponent().inject(this);
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void getProductEntity(ResultsCallback callback) {
        appDb.productDao().getProduct(productId).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(product -> callback.
                onResultsLoaded(Collections.singletonList(product)));
    }
}
