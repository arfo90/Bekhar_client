package com.example.amireza.bekhar_app;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    private final GoogleApiClient mMapApi;

    public TodoItemAdapter(Context c, List<TodoItem> items, GoogleApiClient mapApi) {
        super(c, 0, items);
        mMapApi = mapApi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItemView itemView = (TodoItemView)convertView;
        if (itemView == null) {
            itemView = itemView.inflate(parent);
        }
        TodoItem item = getItem(position);
        itemView.setItem(item.getName(), item.getCategory(), getDistance(item));
        return itemView;
    }

    private String getDistance(TodoItem item) {
        String result = "--";

        if (item.locationKnown()) {
            Location loc = LocationServices.FusedLocationApi.getLastLocation(mMapApi);

            if (loc != null) {
                Coordinates c = new Coordinates(loc.getLatitude(), loc.getLongitude());
                double meters = item.getCoordinates().metersTo(c);

                result = String.format("%.02f", meters / 1000);
            }
        }

        return result + " km";
    }
}
