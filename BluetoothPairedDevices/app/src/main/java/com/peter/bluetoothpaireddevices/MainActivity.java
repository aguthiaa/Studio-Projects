package com.peter.bluetoothpaireddevices;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private static final int REQUEST_ENABLE_BT=1;

    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView1);

        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        textView.append("\nAdapter : "+bluetoothAdapter);
        
        checkBluetoothState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT){
            checkBluetoothState();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void checkBluetoothState() {

        if (bluetoothAdapter == null){

            textView.append("\n Bluetooth not supported. Aborting...");

            return;
        }

        else {
            if (bluetoothAdapter.isEnabled()){
                textView.append("\n Bluetooth is enabled");


                //listing paired devices

                textView.append("\n Paired devices are : ");

                Set<BluetoothDevice> devices=bluetoothAdapter.getBondedDevices();


                for (BluetoothDevice device : devices){
                    textView.append("\n Device : "+device.getName()+" , "+device);
                }
            }

            else {
                //promp user to turn on bluetooth

                Intent enableBluetooth=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                startActivityForResult(enableBluetooth,REQUEST_ENABLE_BT);
            }
        }
    }
}
