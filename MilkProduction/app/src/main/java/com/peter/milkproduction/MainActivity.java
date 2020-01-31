package com.peter.milkproduction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity

{
    private EditText morningProduction, eveningProduction, foodSupplements;
    private Button saveData, viewData;

    private String saveCurrentDate, saveCurrentTime, randomKey;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }



    private void initViews()

    {

        morningProduction = (EditText) findViewById(R.id.morning_production);
        eveningProduction = (EditText) findViewById(R.id.evening_production);
        foodSupplements = (EditText) findViewById(R.id.food_supplements);
        saveData = (Button) findViewById(R.id.submit_btn);
        viewData = (Button) findViewById(R.id.view_data);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String morningUpdate = morningProduction.getText().toString().trim();
                String eveningUpdate = eveningProduction.getText().toString().trim();
                String foodUpdate = foodSupplements.getText().toString().trim();

                storeToPhoneStorage(morningUpdate,eveningUpdate,foodUpdate);
            }
        });


        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( MainActivity.this, DataActivity.class);

                intent.putExtra("randomKey",randomKey);
                startActivity(intent);
            }
        });
    }

    private void storeToPhoneStorage(String morningUpdate, String eveningUpdate, String foodUpdate)

    {
        FileOutputStream fileOutputStream;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calForTime.getTime());

        randomKey = saveCurrentDate + " " + saveCurrentTime;


        try
        {

                fileOutputStream = openFileOutput(randomKey + "MilkProduction", Context.MODE_PRIVATE);


                if (!fileOutputStream.equals(randomKey)) {


                fileOutputStream.write(morningUpdate.getBytes());
                fileOutputStream.write(eveningUpdate.getBytes());
                fileOutputStream.write(foodUpdate.getBytes());

                fileOutputStream.close();

                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_LONG).show();
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
