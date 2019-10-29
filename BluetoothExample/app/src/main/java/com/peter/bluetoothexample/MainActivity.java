package com.peter.bluetoothexample;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVERABLE_BT=0;

    private TextView textView;
    private Button turnOn,discoverable, turnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        final BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null){
            textView.append("device not supported");
        }

        turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mBluetoothAdapter.isEnabled()){
                    Intent enableBluetooth=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBluetooth,REQUEST_ENABLE_BT);

                    Toast.makeText(MainActivity.this, "Bluetooth On", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "problem starting bluetooth", Toast.LENGTH_LONG).show();
                }

            }
        });


        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               mBluetoothAdapter.disable();
                Toast.makeText(getApplicationContext(), "Turning Off Bluetooth", Toast.LENGTH_LONG).show();

            }
        });


        discoverable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Making your device discoverable", Toast.LENGTH_LONG).show();

                if (!mBluetoothAdapter.isDiscovering()){
                    Intent discovering=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(discovering,REQUEST_DISCOVERABLE_BT);
                }
                else {
                    Toast.makeText(MainActivity.this, "No bluetooth devices available", Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    private void initViews() {

        textView=findViewById(R.id.textView1);
        turnOn=findViewById(R.id.turnOnButton);
        discoverable=findViewById(R.id.discoverableButton);
        turnOff=findViewById(R.id.turnOffButton);
    }
}
