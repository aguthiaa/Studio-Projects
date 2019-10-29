package com.peter.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private  double result;
    private EditText firstEntry,secondEntry;
    private Button add,minus,multiply,divide,modulus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input1=firstEntry.getText().toString().trim();
                String input2=secondEntry.getText().toString().trim();

                double a=Double.parseDouble(input1);
                double b=Double.parseDouble(input2);

                result=a + b;

                Toast.makeText(getApplicationContext(), "Sum is : "+result, Toast.LENGTH_LONG).show();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input1=firstEntry.getText().toString().trim();
                String input2=secondEntry.getText().toString().trim();

                double a=Double.parseDouble(input1);
                double b=Double.parseDouble(input2);

                result =a - b;

                Toast.makeText(getApplicationContext(), "Result is : "+result, Toast.LENGTH_LONG).show();
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input1=firstEntry.getText().toString().trim();
                String input2=secondEntry.getText().toString().trim();

                double a=Double.parseDouble(input1);
                double b=Double.parseDouble(input2);

                result= a * b;

                Toast.makeText(getApplicationContext(), "Product is : "+result, Toast.LENGTH_LONG).show();
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input1=firstEntry.getText().toString().trim();
                String input2=secondEntry.getText().toString().trim();

                double a=Double.parseDouble(input1);
                double b=Double.parseDouble(input2);


                result= a / b;

                Toast.makeText(getApplicationContext(), "Result is : "+result, Toast.LENGTH_LONG).show();
            }
        });

        modulus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input1=firstEntry.getText().toString().trim();
                String input2=secondEntry.getText().toString().trim();

                double a=Double.parseDouble(input1);
                double b=Double.parseDouble(input2);


                result=a % b;

                Toast.makeText(getApplicationContext(), "Result is : "+result, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViews() {
        firstEntry=findViewById(R.id.editText1);
        secondEntry=findViewById(R.id.editText2);
        add=findViewById(R.id.buttonAdd);
        minus=findViewById(R.id.buttonMinus);
        multiply=findViewById(R.id.buttonMultiply);
        divide=findViewById(R.id.buttonDivide);
        modulus=findViewById(R.id.buttonModulus);
    }
}
