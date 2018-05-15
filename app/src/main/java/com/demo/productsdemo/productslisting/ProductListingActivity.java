package com.demo.productsdemo.productslisting;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.productsdemo.R;
import com.demo.productsdemo.base.BaseActivity;
import com.demo.productsdemo.base.ErrorCallback;
import com.demo.productsdemo.base.ResultsCallback;
import com.demo.productsdemo.constants.IntentConstants;
import com.demo.productsdemo.productdetails.ProductDetailsActivity;
import com.demo.productsdemo.uimodel.ProductUiModel;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListingActivity extends BaseActivity implements Observer<List<ProductUiModel>>{

    @BindView(R.id.rv_products_list)
    RecyclerView rvProductsList;
    @BindView(R.id.et_search)
    EditText etSearch;

    private ProductListingViewModel viewModel;
    private ProductListAdapter adapter;
    private ErrorCallback errorCallback = errorMessage -> showErrorDialog(errorMessage);

    private ResultsCallback resultsCallback = new ResultsCallback() {
        @Override
        public void onResultsLoaded(List<?> results) {
            if(results != null && results.size() > 0 && results.get(0) instanceof ProductUiModel)
                adapter.updateList((List<ProductUiModel>) results);
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable search) {
            viewModel.getProductsList(search.toString(), resultsCallback);
        }
    };

    private View.OnClickListener productItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = rvProductsList.getChildLayoutPosition(view);
            startDetailsActivity(adapter.getItemAt(position));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ProductListingViewModel.class);
        viewModel.fetchProducts(errorCallback);
        viewModel.getProductsListLiveData().observe(this, this);
        adapter = new ProductListAdapter();
        adapter.setOnItemClickListener(productItemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvProductsList.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvProductsList.getContext(),
                LinearLayoutManager.VERTICAL);
        rvProductsList.addItemDecoration(dividerItemDecoration);
        rvProductsList.setAdapter(adapter);
        etSearch.addTextChangedListener(textWatcher);
    }

    @Override
    public void onChanged(@Nullable List<ProductUiModel> productUiModels) {
        if(productUiModels != null) {
            viewModel.setAllProducts(productUiModels);
            adapter.updateList(productUiModels);
        }
    }

    @OnClick(R.id.sort)
    public void sort(View view) {
        String message = viewModel.sortingApplied()? getString(R.string.sorting_removed) :
                getString(R.string.sorting_applied);

        Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        List<ProductUiModel> productUiModels = new ArrayList<>();
        productUiModels.addAll(viewModel.getAllProducts());
        if(!viewModel.sortingApplied()) {
            Collections.sort(productUiModels, (o1, o2) -> {
                NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                try {
                    double price1 = (double) format.parse(o1.getRegularPrice());
                    double price2 = (double) format.parse(o2.getRegularPrice());
                    if (price1 > price2)
                        return 1;
                    else return -1;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            });
        }
        adapter.updateList(productUiModels);
        viewModel.setSorting(!viewModel.sortingApplied());
    }

    @OnClick(R.id.filter)
    public void filter(View view){
        String message = viewModel.filterApplied()? getString(R.string.filter_removed) :
                getString(R.string.filter_applied);

        Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        if(!viewModel.filterApplied())
            viewModel.getProductsOnSale(resultsCallback);
        else
            adapter.updateList(viewModel.getAllProducts());
        viewModel.setFilterApplied(!viewModel.filterApplied());
    }

    private void startDetailsActivity(ProductUiModel productUiModel){
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(IntentConstants.PRODUCT_ID, productUiModel.getId());
        startActivity(intent);
    }

}
