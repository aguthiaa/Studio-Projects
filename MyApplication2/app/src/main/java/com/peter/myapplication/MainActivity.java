package com.peter.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private TextView firstEntry, secondEntry, operator;
    private Button okayBtn;
    private String input1, input2, sign;

    private int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


    }

    private void initViews() {
        result = (EditText) findViewById(R.id.result_view);
        firstEntry = (TextView) findViewById(R.id.one);
        secondEntry = (TextView) findViewById(R.id.two);
        operator = (TextView) findViewById(R.id.plus);
        okayBtn = (Button) findViewById(R.id.equals_btn);

        firstEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                input1 = firstEntry.getText().toString().trim();
                result.setText(input1);

            }
        });

        secondEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                input2 = secondEntry.getText().toString().trim();
                result.setText(input2);
            }
        });

        operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sign = operator.getText().toString().trim();
                result.setText(sign);

            }
        });

        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer = Integer.parseInt(input1) + Integer.parseInt(input2);
                result.setText(answer);
            }
        });



    }


}
