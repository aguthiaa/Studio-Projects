package com.peter.milkproduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataActivity extends AppCompatActivity {

    private TextView milkData;

    private String randomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        randomKey = getIntent().getExtras().get("randomKey").toString();

        milkData = (TextView) findViewById(R.id.milk_data_view);


//        StringBuffer stringBuffer = new StringBuffer();

        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(randomKey + "MilkProduction")));

            String inputString;

            while ((inputString = inputReader.readLine()) != null) {
//                stringBuffer.append(inputString + "\n");
                milkData.setText("Date "+ randomKey + " Production "+inputString + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Toast.makeText( getApplicationContext(), stringBuffer.toString(), Toast.LENGTH_LONG).show();
    }
}
