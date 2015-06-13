package com.example.amireza.bekhar_app;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class TodoListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);
        List<TodoItem> items = Arrays.asList(
                new TodoItem("orange", "grocery"),
                new TodoItem("badminton racket", "sports")
        );
        setListAdapter(new TodoItemAdapter(getActivity(), items));

        return v;
    }
}
