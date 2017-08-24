package com.qcosmos.vpb.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    Button saveBtn;
    EditText edTxtItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final int itemIndex = getIntent().getIntExtra(GlobalData.INDEX_KEY, GlobalData.ILLEGAL_INDEX);
        final String itemName = ((ToDoItem)getIntent().getSerializableExtra(GlobalData.ITEM_KEY)).item;

        saveBtn = (Button) findViewById(R.id.btnSaveItem);
        edTxtItem = (EditText) findViewById(R.id.edTxtItem);

        edTxtItem.setText(itemName);
        edTxtItem.setCursorVisible(true);
        edTxtItem.setSelection(itemName.length());

        edTxtItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showKeyboard(v, EditActivity.this);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemValue = edTxtItem.getText().toString();
                if (itemValue == null || itemValue.trim().isEmpty()) {
                    edTxtItem.setText("");
                    return;
                }

                Utils.dismissKeyboard(v, EditActivity.this);
                onSubmit(itemIndex, itemValue.trim());
            }
        });

    }

    private void onSubmit(int itemIndex, String itemValue) {
        Intent data = new Intent();
        data.putExtra(GlobalData.ITEM_KEY, itemValue);
        data.putExtra(GlobalData.INDEX_KEY, itemIndex);
        setResult(RESULT_OK, data);
        this.finish();
    }

}
