package com.demo.productsdemo.productslisting;

import com.demo.productsdemo.room.ProductEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishal on 13-05-2018.
 */

public class GetProductsResponse {

    @SerializedName("products")
    @Expose
    private List<ProductEntity> products = null;

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

}
