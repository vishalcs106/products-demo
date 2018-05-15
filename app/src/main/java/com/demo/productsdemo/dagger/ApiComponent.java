package com.demo.productsdemo.dagger;


import com.demo.productsdemo.productdetails.ProductDetailsViewModel;
import com.demo.productsdemo.productslisting.ProductListingViewModel;

import dagger.Component;

/**
 * Created by Vishal on 13-05-2018.
 */
@Component(modules = ApiModule.class, dependencies = {RepositoryComponent.class})
@CustomScope
public interface ApiComponent {
    void inject(ProductListingViewModel viewModel);

    void inject(ProductDetailsViewModel productDetailsViewModel);
}
