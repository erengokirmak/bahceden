package com.swifties.bahceden.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Objects;

public class Producer extends User {
    String displayName;
    String backgroundImageUrl;
    double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    String city; // TODO this is tentative we can change this into a location object etc
    ArrayList<Product> products;
    ArrayList<Order> orders;

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
