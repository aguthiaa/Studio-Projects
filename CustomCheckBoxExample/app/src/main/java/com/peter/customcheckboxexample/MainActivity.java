package com.peter.customcheckboxexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox pizza,coffee,burger;
    private Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder orderSummary=new StringBuilder();

                orderSummary.append("Selected Items: ");

                int totalAmmount = 0;

                if (pizza.isChecked()){

                    totalAmmount += 500;
                    orderSummary.append("\nPizza Ksh. 500");
                }

                if (coffee.isChecked()){

                    totalAmmount += 100;

                    orderSummary.append("\nCoffee Ksh. 100");
                }

                if (burger.isChecked()){

                    totalAmmount += 250;

                    orderSummary.append("\nBurger Ksh. 250");
                }

                orderSummary.append("\nTotal Ammount: "+totalAmmount+" Shillings");
                Toast.makeText(getApplicationContext(), orderSummary, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViews() {
        pizza=findViewById(R.id.pizzaCheckBox);
        coffee=findViewById(R.id.coffeeCheckBox);
        burger=findViewById(R.id.burgerCheckBox);
        order=findViewById(R.id.orderButton);
    }
}
