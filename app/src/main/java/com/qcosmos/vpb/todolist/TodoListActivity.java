package com.qcosmos.vpb.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    //ArrayList for list of to-do items
    ArrayList<String> todoItems;

    //ArrayAdapter to map to-do-item-list to list-view
    ArrayAdapter<String> arrayTodoAdapter;

    ListView lvItems;
    EditText edEditText;
    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list); // ties layout to activity

        populateTodoItems();

        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(arrayTodoAdapter);
        edEditText = (EditText) findViewById(R.id.etEditText);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);

        edEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showKeyboard(v, TodoListActivity.this);
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.dismissKeyboard(v, TodoListActivity.this);
                onAddItem(v);
            }
        });

        //registering the long press to delete item action
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                arrayTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        //registering the item-click to edit item action
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentEdit = new Intent(TodoListActivity.this, EditActivity.class);
                intentEdit.putExtra(GlobalData.INDEX_KEY, position);
                intentEdit.putExtra(GlobalData.ITEM_KEY, todoItems.get(position));
                startActivityForResult(intentEdit, GlobalData.REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && reqCode == GlobalData.REQUEST_CODE) {
            String updatedItemVal = data.getExtras().getString(GlobalData.ITEM_KEY);
            int updatedItemIndex = data.getExtras().getInt(GlobalData.INDEX_KEY, GlobalData.ILLEGAL_INDEX);
            onUpdateItem(updatedItemVal, updatedItemIndex);
        } else {
            System.out.println("Received bad REQUEST_CODE/RESULT_STATUS");
        }
    }

    private  void populateTodoItems() {
        todoItems = new ArrayList<>();
        readItems();
        arrayTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems); // TODO: check
    }

    private void onAddItem(View view) {
        //view in this case will be button
        arrayTodoAdapter.add(edEditText.getText().toString());
        edEditText.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, GlobalData.FILE_NAME);
        try {
            todoItems = new ArrayList<>(FileUtils.readLines(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateItem(String newVal, int index) {
        todoItems.remove(index);
        todoItems.add(index, newVal);
        arrayTodoAdapter.notifyDataSetChanged();
        writeItems();
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, GlobalData.FILE_NAME);
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
