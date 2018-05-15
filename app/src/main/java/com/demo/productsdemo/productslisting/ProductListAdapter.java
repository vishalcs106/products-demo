package com.demo.productsdemo.productslisting;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.productsdemo.R;
import com.demo.productsdemo.uimodel.ProductUiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vishal on 13-05-2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductsViewHolder>{
   private List<ProductUiModel> products = new ArrayList<>();
   private View.OnClickListener productItemClickListener;

   @Override
   public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new ProductsViewHolder(LayoutInflater.from(parent.getContext())
              .inflate(R.layout.product_list_item, parent, false));
   }

   @Override
   public void onBindViewHolder(ProductsViewHolder holder, int position) {
      holder.bind(products.get(position));
   }

   @Override
   public int getItemCount() {
      return products.size();
   }

    public void updateList(List<ProductUiModel> products){
        this.products = products;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.productItemClickListener = onItemClickListener;
    }

    public ProductUiModel getItemAt(int position){
        return products.get(position);
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder{
      @BindView(R.id.tv_product_name)
      TextView tvProductName;
      @BindView(R.id.tv_product_price)
      TextView tvProductPrice;
      @BindView(R.id.iv_product_image)
      ImageView ivProductImage;
      public ProductsViewHolder(View itemView) {
         super(itemView);
         ButterKnife.bind(this, itemView);
         if(productItemClickListener != null)
            itemView.setOnClickListener(productItemClickListener);
      }
      public void bind(ProductUiModel productUiModel){
         tvProductName.setText(productUiModel.getName());
         tvProductPrice.setText(String.valueOf(productUiModel.getRegularPrice()));
         Glide.with(itemView.getContext()).load(productUiModel.getImageUrl()).into(ivProductImage);
      }
   }

}
