package com.example.amireza.bekhar_app;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class TodoListFragment extends ListFragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mMapApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);
        mMapApi = new GoogleApiClient.Builder(v.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mMapApi.connect();

        return v;
    }

    private void initializeList() {
        List<TodoItem> items = Arrays.asList(
                new TodoItem("orange", "grocery", new Coordinates(3.1333, 101.6833)),
                new TodoItem("badminton racket", "sports", new Coordinates(1.3000, 103.8000))
        );
        setListAdapter(new TodoItemAdapter(getActivity(), items, mMapApi));
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        // Connected to Google Play services!
        // The good stuff goes here.
        initializeList();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection has been interrupted.
        // Disable any UI components that depend on Google APIs
        // until onConnected() is called.
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // This callback is important for handling errors that
        // may occur while attempting to connect with Google.
        //
        // More about this in the 'Handle Connection Failures' section.
    }
}
