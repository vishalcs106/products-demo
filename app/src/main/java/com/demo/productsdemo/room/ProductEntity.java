


package com.demo.productsdemo.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vishal on 13-05-2018.
 */
@Entity

public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("code_color")
    @Expose
    private String codeColor;
    @SerializedName("color_slug")
    @Expose
    private String colorSlug;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("on_sale")
    @Expose
    private Boolean onSale;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("actual_price")
    @Expose
    private String actualPrice;
    @SerializedName("discount_percentage")
    @Expose
    private String discountPercentage;
    @SerializedName("installments")
    @Expose
    private String installments;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = null;

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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCodeColor() {
        return codeColor;
    }

    public void setCodeColor(String codeColor) {
        this.codeColor = codeColor;
    }

    public String getColorSlug() {
        return colorSlug;
    }

    public void setColorSlug(String colorSlug) {
        this.colorSlug = colorSlug;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public class Size {

        @SerializedName("available")
        @Expose
        private Boolean available;
        @SerializedName("size")
        @Expose
        private String size;
        @SerializedName("sku")
        @Expose
        private String sku;

        public Boolean getAvailable() {
            return available;
        }

        public void setAvailable(Boolean available) {
            this.available = available;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }
    }
}
