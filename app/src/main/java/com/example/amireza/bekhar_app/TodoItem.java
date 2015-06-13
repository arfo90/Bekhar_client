package com.example.amireza.bekhar_app;

public class TodoItem {

    public TodoItem(String name, String category) {
        mName = name;
        mCategory = category;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getName() {
        return mName;
    }

    private final String mCategory;
    private final String mName;
}
