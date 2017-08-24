package com.qcosmos.vpb.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    //CustomAdapter to map to-do-item-list to list-view
    ToDoListAdapter toDoListAdapter;

    ListView lvItems;
    EditText edEditText;
    Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list); // ties layout to activity

        populateTodoItems();

        lvItems = (ListView) findViewById(R.id.lvItems);
        edEditText = (EditText) findViewById(R.id.etEditText);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);

        lvItems.setAdapter(toDoListAdapter);

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
                onAddItem();
            }
        });

        //registering the long press to delete item action
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                GlobalData.todoItems.remove(position);
                toDoListAdapter.notifyDataSetChanged();
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
                intentEdit.putExtra(GlobalData.ITEM_KEY, GlobalData.todoItems.get(position));
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
        GlobalData.todoItems = (ArrayList<ToDoItem>) ToDoItem.loadAll(this);
        toDoListAdapter = new ToDoListAdapter(this, GlobalData.todoItems);
    }

    private void onAddItem() {
        String itemValue = edEditText.getText().toString();
        if (itemValue == null || itemValue.trim().isEmpty()) {
            edEditText.setText("");
            return;
        }
        toDoListAdapter.add(new ToDoItem(itemValue.trim()));
        edEditText.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, GlobalData.FILE_NAME);
        try {
            List<String> lines = FileUtils.readLines(file);
            GlobalData.todoItems = new ArrayList<ToDoItem>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onUpdateItem(String newVal, int index) {
        GlobalData.todoItems.remove(index);
        GlobalData.todoItems.add(index, new ToDoItem(newVal));
        toDoListAdapter.notifyDataSetChanged();
        writeItems();
    }

    private void writeItems() {
        ToDoItem.saveAll(this, GlobalData.todoItems);
    }
}
