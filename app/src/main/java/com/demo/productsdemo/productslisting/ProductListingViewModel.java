package com.demo.productsdemo.productslisting;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.demo.productsdemo.App;
import com.demo.productsdemo.R;
import com.demo.productsdemo.base.ErrorCallback;
import com.demo.productsdemo.base.ResultsCallback;
import com.demo.productsdemo.room.AppDb;
import com.demo.productsdemo.room.ProductEntity;
import com.demo.productsdemo.uimodel.ProductUiModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by Vishal on 13-05-2018.
 */

public class ProductListingViewModel extends AndroidViewModel {
    @Inject
    Retrofit retrofit;
    @Inject
    AppDb appDb;
    @Inject
    GetProductsService getProductsService;

    private List<ProductUiModel> allProducts = new ArrayList<>();

    private boolean sortingApplied = false;
    private boolean filterApplied = false;

    public ProductListingViewModel(@NonNull Application application) {
        super(application);
        resolveDependencies();
    }

    private void resolveDependencies(){
        App.getApiComponent().inject(this);
    }

    public LiveData<List<ProductUiModel>> getProductsListLiveData() {
        return appDb.productDao().getAllProducts();
    }

    public void getProductsList(String searchString, ResultsCallback resultsCallback) {
        appDb.productDao().getProductsWith("%"+searchString+"%").subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(users -> resultsCallback.onResultsLoaded(users));
    }

    public void getProductsOnSale(ResultsCallback resultsCallback) {
        appDb.productDao().getProductsOnSale().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(users -> resultsCallback.onResultsLoaded(users));
    }

    public void fetchProducts(ErrorCallback errorCallback) {
        getProductsService.getProducts().enqueue(new Callback<GetProductsResponse>() {
            @Override
            public void onResponse(Call<GetProductsResponse> call, Response<GetProductsResponse> response) {
                List<ProductEntity> productEntities = response.body().getProducts();
                insertProducts(productEntities);
            }

            @Override
            public void onFailure(Call<GetProductsResponse> call, Throwable t) {
                errorCallback.onError(getApplication().getString(R.string.error));
            }
        });
    }

    private void insertProducts(List<ProductEntity> productEntities) {
        Completable.fromAction(()->
            appDb.productDao().updateProducts(productEntities));
    }

    public List<ProductUiModel> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<ProductUiModel> allProducts) {
        this.allProducts = allProducts;
    }

    public boolean sortingApplied() {
        return sortingApplied;
    }

    public void setSorting(boolean sortingApplied) {
        this.sortingApplied = sortingApplied;
    }

    public boolean filterApplied(){
         return filterApplied;
    }

    public void setFilterApplied(boolean filterApplied){
        this.filterApplied = filterApplied;
    }
}
