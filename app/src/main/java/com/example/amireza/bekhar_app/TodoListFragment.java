package com.example.amireza.bekhar_app;

import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class TodoListFragment extends ListFragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int DONE = 1;
    private GoogleApiClient mMapApi;
    private Handler mHttpListener = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DONE:
                    mHttpListener = null;
                    initializeList();
                    break;
            }
        }
    };
    private List<Category> mCategories = null;
    private boolean mConnected = false;

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

        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://bekhar.eu-gb.mybluemix.net/api/category");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    int code = con.getResponseCode();
                    String response = readStream(con.getInputStream());
                    JSONArray jsonArr = new JSONArray(response);
                    List<Category> result = new ArrayList<Category>();

                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject obj = jsonArr.getJSONObject(i);
                        result.add(new Category(obj.getString("id"), obj.getString("name")));
                    }
                    mCategories = result;
                } catch (IOException|JSONException ex) {
                    ex.printStackTrace();
                } finally {
                    mHttpListener.sendEmptyMessage(DONE);
                }
            }

            private String readStream(InputStream in) throws IOException {
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();
            }
        });
        thread.start();

        return v;
    }

    private void initializeList() {
        if (mConnected && mHttpListener == null) {
            List<TodoItem> items = Arrays.asList(
                    new TodoItem("orange", "grocery", new Coordinates(3.1333, 101.6833)),
                    new TodoItem("badminton racket", "sports", new Coordinates(1.3000, 103.8000))
            );
            setListAdapter(new TodoItemAdapter(getActivity(), items, mMapApi));
        }
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        // Connected to Google Play services!
        // The good stuff goes here.
        mConnected = true;
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
        onConnected(null);  // for emulator...
    }
}
