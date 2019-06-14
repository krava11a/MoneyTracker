package com.krava11.moneytracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    private static final String TAG = "AddItemActivity";
    public static final String TYPE_KEY = "key";

    private EditText name;
    private EditText price;
    private Button addBtn;

    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_item_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        addBtn = findViewById(R.id.add_btn);

        type=getIntent().getExtras().getString(TYPE_KEY);

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                if ((!TextUtils.isEmpty(name.getText())) && (!TextUtils.isEmpty(price.getText()))) {
                    addBtn.setEnabled(true);
                } else {
                    addBtn.setEnabled(false);
                }
            }
        };

        name.addTextChangedListener(textWatcher);
        price.addTextChangedListener(textWatcher);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = name.getText().toString();
                String priceValue = price.getText().toString();

                Item item = new Item(nameValue,priceValue,type);

                Intent intent = new Intent();
                intent.putExtra("item",  item);


                setResult(RESULT_OK, intent);
                finish();
            }
        });
//        name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                addBtn.setEnabled(!TextUtils.isEmpty(s));
//            }
//        });

//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String itemName = name.getText().toString();
//                String itemPrice = price.getText().toString();
//                Log.i(TAG, "onClick: "+itemName+":"+itemPrice);
//            }
//        });
    }
}
