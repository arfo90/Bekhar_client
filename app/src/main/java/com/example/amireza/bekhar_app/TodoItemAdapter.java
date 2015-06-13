package com.example.amireza.bekhar_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    public TodoItemAdapter(Context c, List<TodoItem> items) {
        super(c, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItemView itemView = (TodoItemView)convertView;
        if (itemView == null) {
            itemView = itemView.inflate(parent);
        }
        itemView.setItem(getItem(position));
        return itemView;
    }
}
