package com.krava11.moneytracker;

import com.google.gson.annotations.SerializedName;

public class Item {

    public static final String TYPE_UNKNOWN = "unknown";
    public static final String TYPE_INCOMES = "incomes";
    public static final String TYPE_EXPENSES = "expenses";

    public int id;
    @SerializedName("name")
    public String title;
    public int price;
    public String type;

    public Item(String name, int price, String type) {
        this.title = name;
        this.price = price;
        this.type = type;
    }
}
