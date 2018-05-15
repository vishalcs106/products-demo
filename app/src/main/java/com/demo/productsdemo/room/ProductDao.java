package com.demo.productsdemo.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.demo.productsdemo.uimodel.ProductUiModel;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Vishal on 13-05-2018.
 */
@Dao
public abstract class ProductDao {

    @Query("SELECT id, name, regularPrice, image FROM ProductEntity")
    public abstract LiveData<List<ProductUiModel>>  getAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertProducts(List<ProductEntity> products);

    @Query("DELETE FROM ProductEntity")
    public abstract void deleteProducts();

    @Transaction
    public void updateProducts(List<ProductEntity> products) {
        deleteProducts();
        insertProducts(products);
    }

    @Query("SELECT id, name, regularPrice, image FROM ProductEntity WHERE name LIKE :searchString")
    public abstract Flowable<List<ProductUiModel>> getProductsWith(String searchString);

    @Query("SELECT id, name, regularPrice, image FROM ProductEntity WHERE onSale = 1")
    public abstract Flowable<List<ProductUiModel>> getProductsOnSale();

    @Query("SELECT * FROM ProductEntity WHERE id = :id")
    public abstract Flowable<ProductEntity> getProduct(int id);

}
