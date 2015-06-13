package com.example.amireza.bekhar_app;

public class Location {

    private final Coordinates mCoordinates;
    private final String mAddress;

    public Location(Coordinates coordinates, String address) {
        mCoordinates = coordinates;
        mAddress = address;
    }

    public double metersFrom(Coordinates coordinates) {
        return mCoordinates.metersTo(coordinates);
    }

    public String getAddress() {
        return mAddress;
    }
}
