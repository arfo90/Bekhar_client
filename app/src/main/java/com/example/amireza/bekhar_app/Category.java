package com.example.amireza.bekhar_app;

public class Category {

    private final String mId;
    private final String mName;

    public Category(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
