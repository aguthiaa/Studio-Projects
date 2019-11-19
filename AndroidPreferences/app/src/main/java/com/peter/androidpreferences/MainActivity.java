package com.peter.androidpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView dataView;
    private Button storeData,showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }


    private void initializeViews() {
        dataView=(TextView) findViewById(R.id.show_data);
        storeData=(Button) findViewById(R.id.store_information);
        showData=(Button) findViewById(R.id.show_information);
    }


}
