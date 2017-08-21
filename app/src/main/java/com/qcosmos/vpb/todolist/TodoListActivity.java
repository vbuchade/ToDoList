package com.qcosmos.vpb.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    //ArrayList for list
    ArrayList<String> todoItems;

    //ArrayAdapter to map list items to list-view
    ArrayAdapter<String> arrayTodoAdapter;

    ListView lvItems;
    EditText edEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list); // ties layout to activity
        populateTodoItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(arrayTodoAdapter);
        edEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                arrayTodoAdapter.notifyDataSetChanged(); //IMP
                writeItems();
                return true;
            }
        });
    }

    public  void populateTodoItems() {
        todoItems = new ArrayList<>();
//        todoItems.add("Item A");
//        todoItems.add("Item B");
//        todoItems.add("Item C");
        readItems();
        arrayTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems); // check
    }

    public void onAddItem(View view) {
        //view in this case will be button
        arrayTodoAdapter.add(edEditText.getText().toString()); // what? why?
        edEditText.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todolist.txt");
        try {
            todoItems = new ArrayList<>(FileUtils.readLines(file)); // Alternate way
        } catch (IOException e) {

        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todolist.txt");
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {

        }
    }
}
