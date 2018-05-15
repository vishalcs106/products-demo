package com.demo.productsdemo.productslisting;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vishal on 13-05-2018.
 */

public interface GetProductsService {
    @GET("59b6a65a0f0000e90471257d")
    Call<GetProductsResponse> getProducts();

}
