package com.example.amireza.bekhar_app;

import android.app.ListFragment;
import android.renderscript.Sampler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class TodoList extends ActionBarActivity implements NewItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
            case R.id.action_add:
                EntryDialog cdd=new EntryDialog(this);
                cdd.show();
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void newItem(List<String> item) {
        TodoListFragment todolist = (TodoListFragment)getFragmentManager().findFragmentById(R.id.fragment);
        todolist.addItem(new TodoItem(item.get(0), item.get(1), new Coordinates(1.0, 1.0)));
    }
}
