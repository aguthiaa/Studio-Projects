package com.peter.togglebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Button buttonSubmit;
    private ToggleButton toggleButton1,toggleButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuffer result=new StringBuffer();

                result.append("ToggleButton1: ").append(toggleButton1.getText());
                result.append("\n\nToggleButton2: ").append(toggleButton2.getText());

                //Displaying on a Toast

                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initViews() {

        buttonSubmit=findViewById(R.id.button1);
        toggleButton1=findViewById(R.id.toggleButton1);
        toggleButton2=findViewById(R.id.toggleButton2);
    }

}
