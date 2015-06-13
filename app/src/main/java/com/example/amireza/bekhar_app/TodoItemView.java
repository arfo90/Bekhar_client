package com.example.amireza.bekhar_app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TodoItemView extends RelativeLayout {

    private TextView mNameView;
    private TextView mCategoryView;

    public static TodoItemView inflate(ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        return (TodoItemView) inf.inflate(R.layout.fragment_todo_list, parent, false);
    }

    public TodoItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.list_row, this, true);

        mNameView = (TextView) findViewById(R.id.item_nameTextView);
        mCategoryView = (TextView) findViewById(R.id.item_categoryTextView);
    }

    public TodoItemView(Context c) {
        this(c, null);
    }

    public TodoItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setItem(TodoItem item) {
        mNameView.setText(item.getName());
        mCategoryView.setText(item.getCategory());
    }
}
