package com.example.amireza.bekhar_app;

public class Coordinates {

    private final double mLatitude;
    private final double mLongitude;

    // Earth radius in meters
    private static final double R = 6371000;

    public Coordinates(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    // See http://www.movable-type.co.uk/scripts/latlong.html
    public double metersTo(Coordinates other) {
        double l1 = Math.toRadians(mLatitude);
        double l2 = Math.toRadians(other.mLatitude);
        double d1 = Math.toRadians(other.mLatitude - mLatitude);
        double d2 = Math.toRadians(other.mLongitude - mLongitude);

        double a = Math.sin(d1 / 2) * Math.sin(d1 / 2) +
                Math.cos(l1) * Math.cos(l2) * Math.sin(d2 / 2) * Math.sin(d2 / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
