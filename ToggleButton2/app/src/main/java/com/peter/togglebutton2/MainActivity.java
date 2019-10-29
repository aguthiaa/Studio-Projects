package com.peter.togglebutton2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private RadioButton buttonOn,buttonOff;
    private ToggleButton toggleButton1,toggleButton2,toggleButton3,toggleButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //Handling radio button events


        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonOn.isChecked()){
                    toggleButton1.getTextOn();
                    toggleButton2.getTextOn();
                    toggleButton3.getTextOn();
                    toggleButton4.getTextOn();
                }
                else {
                    toggleButton1.getTextOff();
                    toggleButton2.getTextOff();
                    toggleButton3.getTextOff();
                    toggleButton4.getTextOff();
                }

            }
        });

        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (buttonOff.isChecked()){

                    toggleButton1.getTextOff();
                    toggleButton2.getTextOff();
                    toggleButton3.getTextOff();
                    toggleButton4.getTextOff();

                }
                else {

                }


            }
        });
        //End of radio button events


        //Handling toggle Button events
        //One at a time

        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String result=toggleButton1.getText().toString();

                if (result.equals("OFF")){

                    Toast.makeText(getApplicationContext(), "ToggleButton1: "+result, Toast.LENGTH_SHORT).show();
                }
                if (result.equals("ON")){
                    Toast.makeText(getApplicationContext(), "ToggleButton1: "+result, Toast.LENGTH_SHORT).show();
                }
            }
        });


        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String result=toggleButton2.getText().toString();

                if (result.equals("OFF")){

                    Toast.makeText(getApplicationContext(), "ToggleButton2: "+result, Toast.LENGTH_SHORT).show();
                }
                if (result.equals("ON")){
                    Toast.makeText(getApplicationContext(), "ToggleButton2: "+result, Toast.LENGTH_SHORT).show();
                }

            }
        });


        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String result=toggleButton3.getText().toString();

                if (result.equals("OFF")){

                    Toast.makeText(getApplicationContext(), "ToggleButton3: "+result, Toast.LENGTH_SHORT).show();
                }
                if (result.equals("ON")){
                    Toast.makeText(getApplicationContext(), "ToggleButton3: "+result, Toast.LENGTH_SHORT).show();
                }

            }
        });


        toggleButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String result=toggleButton4.getText().toString();

                if (result.equals("OFF")){

                    Toast.makeText(getApplicationContext(), "ToggleButton4: "+result, Toast.LENGTH_SHORT).show();
                }
                if (result.equals("ON")){
                    Toast.makeText(getApplicationContext(), "ToggleButton4: "+result, Toast.LENGTH_SHORT).show();
                }

            }
        });

        //End off all toggle button events
    }

    private void initViews() {

        buttonOn=findViewById(R.id.radioButton1);
        buttonOff=findViewById(R.id.radioButton2);
        toggleButton1=findViewById(R.id.toggleButton1);
        toggleButton2=findViewById(R.id.toggleButton2);
        toggleButton3=findViewById(R.id.toggleButton3);
        toggleButton4=findViewById(R.id.toggleButton4);
    }
}
