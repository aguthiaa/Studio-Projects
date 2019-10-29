package com.peter.checkboxexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    
    private CheckBox pizza,coffee,burger;
    private Button order;
    private TextView orderSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int totalAmmount=0;

                StringBuilder result=new StringBuilder();

                result.append("Selected Items: ");

                if (pizza.isChecked()){
                    result.append("\nPizza Ksh.500");
                    totalAmmount += 500;
                }
                if (coffee.isChecked()){
                    result.append("\nCoffee Ksh.100");
                     totalAmmount += 100;
                }

                if (burger.isChecked()){
                    result.append("\nBurger Ksh.250");

                    totalAmmount += 250;
                }

                result.append("\nTotal Ammount : "+totalAmmount+" Shillings");

                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                orderSummary.setText(result);
            }
        });
    }

    private void initViews() {

        pizza=findViewById(R.id.checkBox1);
        coffee=findViewById(R.id.checkBox2);
        burger=findViewById(R.id.checkBox3);
        order=findViewById(R.id.button1);
        orderSummary=findViewById(R.id.textView1);
    }
}
