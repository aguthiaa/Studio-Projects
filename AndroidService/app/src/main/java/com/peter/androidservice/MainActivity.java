package com.peter.androidservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button startService, stopService, nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        nextPage.setOnClickListener(this);
    }

    public void onClick(View src){

        switch (src.getId()){

            case R.id.buttonStart:
                startService(new Intent(this,MyService.class));

                break;case R.id.buttonStop:
                stopService(new Intent(this,MyService.class));

                break;case R.id.buttonNext:

                Intent intent=new Intent(MainActivity.this,NextPageActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void initViews() {

        startService=findViewById(R.id.buttonStart);
        stopService=findViewById(R.id.buttonStop);
        nextPage=findViewById(R.id.buttonNext);

    }

}
