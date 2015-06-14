package com.example.amireza.bekhar_app;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amireza on 6/13/2015.
 */
public class EntryDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button add;
    public EditText itemName;
    public TextView textView;
    ArrayList<String> categoryList = new ArrayList<String> ();

    public EntryDialog(Activity act) {
        super(act);
        // TODO Auto-generated constructor stub
        this.c = act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.entery_dialog);
        itemName = (EditText)findViewById(R.id.item);
        add = (Button)findViewById(R.id.add_button);
        textView = (TextView)findViewById(R.id.tv_test);
        add.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.cat_spiner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, categoryList);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        getCategoryJson();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_button:
                //textView.setText(itemName.getText());
                ((TodoList)c).newItem(new ArrayList<String>() {{add(((TextView)findViewById(R.id.item)).getText().toString()); add((String)((Spinner)findViewById(R.id.cat_spiner)).getSelectedItem());}});
                this.dismiss();
                break;
            default:
        }

        //dismiss();
    }

    public void getCategoryJson(){

        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        String url = "http://bekhar.eu-gb.mybluemix.net/api/category";

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>()
        {

            @Override
            public void onResponse(JSONArray response) {
                //Log.d("", response.toString());
                // TODO Auto-generated method stub
                //textView.setText("Response => " + response.toString());
                //findViewById(R.id.progressBar1).setVisibility(View.GONE);
                for (int index = 0; index < response.length(); ++index) {
                    try {
                        JSONObject category = response.getJSONObject(index);
                        categoryList.add(category.getString("name"));
                    } catch (JSONException e) {
                        ;
                    }
                }

            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }

        };
        JsonArrayRequest  jsObjRequest =new JsonArrayRequest(url, listener, errorListener) ;
        queue.add(jsObjRequest);

    }
}