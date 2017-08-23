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
        final String itemName = getIntent().getStringExtra(GlobalData.ITEM_KEY);

        saveBtn = (Button) findViewById(R.id.btnSaveItem);
        edTxtItem = (EditText) findViewById(R.id.edTxtItem);

        edTxtItem.setText(itemName);
        edTxtItem.setCursorVisible(true);
        edTxtItem.setSelection(itemName.length());


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit(itemIndex);
            }
        });

    }

    private void onSubmit(int itemIndex) {
        Intent data = new Intent();
        data.putExtra(GlobalData.ITEM_KEY, edTxtItem.getText().toString());
        data.putExtra(GlobalData.INDEX_KEY, itemIndex);
        setResult(RESULT_OK, data);
        this.finish();
    }

}