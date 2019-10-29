package com.peter.toastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toast toast= Toast.makeText(getApplicationContext(),"Toast view", Toast.LENGTH_LONG);

       // toast.setMargin(50,50);
        toast.show();

    }
}
