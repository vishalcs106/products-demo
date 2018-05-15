package com.demo.productsdemo.productdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.productsdemo.R;
import com.demo.productsdemo.base.BaseActivity;
import com.demo.productsdemo.base.ResultsCallback;
import com.demo.productsdemo.constants.IntentConstants;
import com.demo.productsdemo.room.ProductEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_product_image) ImageView ivProductImage;
    @BindView(R.id.tv_product_name) TextView tvProductName;
    @BindView(R.id.tv_product_price) TextView tvProductPrice;
    @BindView(R.id.tv_promotional_price) TextView tvPromotionalPrice;
    @BindView(R.id.tv_on_sale) TextView tvOnSale;
    @BindView(R.id.tv_available_sizes) TextView tvAvailableSizes;

    private ProductDetailsViewModel viewModel;

    private ResultsCallback resultsCallback = results -> {
        if(results != null && results.size() > 0 && results.get(0) instanceof ProductEntity)
            populateProductDetails((ProductEntity) results.get(0));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
        processIntent();
        viewModel.getProductEntity(resultsCallback);
    }

    private void populateProductDetails(ProductEntity productEntity) {
        Glide.with(this).load(productEntity.getImage()).into(ivProductImage);
        tvProductName.setText(productEntity.getName());
        tvProductPrice.setText(productEntity.getActualPrice());
        if(productEntity.getOnSale()) {
            tvOnSale.setText(getString(R.string.product_on_sale));
            tvPromotionalPrice.setText(productEntity.getActualPrice());
        }
        tvAvailableSizes.setText(getAvailableSizesAsString(productEntity.getSizes()));
    }

    private String getAvailableSizesAsString(List<ProductEntity.Size> sizes) {
        String sizesString = "";
        for(ProductEntity.Size size : sizes){
            if(size.getAvailable())
                sizesString += size.getSize()+", ";
        }
        return sizesString.substring(0, sizesString.length()-2);
    }

    private void processIntent() {
        viewModel.setProductId(getIntent().getIntExtra(IntentConstants.PRODUCT_ID, -1));
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
