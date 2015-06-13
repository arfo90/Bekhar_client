package com.example.amireza.bekhar_app;

public class TodoItem {

    public TodoItem(String name, String category, Location location) {
        mName = name;
        mCategory = category;
        mLocation = location;
    }

    public TodoItem(String name, String category) {
        this(name, category, null);
    }

    public String getCategory() {
        return mCategory;
    }

    public String getName() {
        return mName;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public boolean locationKnown() {
        return mLocation != null;
    }

    private final String mCategory;
    private final String mName;
    private Location mLocation;
}
