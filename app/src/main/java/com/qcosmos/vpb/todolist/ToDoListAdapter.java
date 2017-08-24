package com.qcosmos.vpb.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vpb on 8/22/17.
 */

public class ToDoListAdapter extends ArrayAdapter {

    public ToDoListAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        ToDoItem toDoItem = GlobalData.todoItems.get(pos);
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.todo_items, viewGroup, false);
        }

        TextView txtVwItem = (TextView) view.findViewById(R.id.txtVwItem);

        txtVwItem.setText(toDoItem.item);

        return view;
    }
}
