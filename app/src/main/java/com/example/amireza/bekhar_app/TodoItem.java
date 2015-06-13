package com.example.amireza.bekhar_app;

public class TodoItem {

    public TodoItem(String name, String category, Coordinates coordinates, String address) {
        mName = name;
        mCategory = category;
        mCoordinates = coordinates;
        mAddress = address;
    }

    public TodoItem(String name, String category, Coordinates coordinates) {
        this(name, category, coordinates, null);
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

    public String getAddress() {
        return mAddress;
    }

    public Coordinates getCoordinates() { return mCoordinates; }

    public void setCoordinates(Coordinates coordinates) {
        mCoordinates = coordinates;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public boolean locationKnown() {
        return mCoordinates != null;
    }

    private final String mCategory;
    private final String mName;
    private String mAddress;
    private Coordinates mCoordinates;
}
