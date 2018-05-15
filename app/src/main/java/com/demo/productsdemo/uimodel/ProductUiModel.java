package com.demo.productsdemo.uimodel;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Vishal on 13-05-2018.
 */

public class ProductUiModel {
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="regularPrice")
    private String regularPrice;
    @ColumnInfo(name="image")
    private String imageUrl;

    public ProductUiModel(int id, String name, String regularPrice, String imageUrl) {
        this.id = id;
        this.name = name;
        this.regularPrice = regularPrice;
        this.imageUrl = imageUrl;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
