package com.domapr.cart;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartData {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("reference")
    @Expose
    private String reference;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
    this.items = items;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}