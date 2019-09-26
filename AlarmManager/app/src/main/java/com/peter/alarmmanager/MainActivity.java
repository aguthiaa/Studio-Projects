package com.peter.alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.button);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a method to start alert
                
                
                startAlert();
            }
        });
    }

    private void startAlert() {

        EditText text=findViewById(R.id.editText);
        int i=Integer.parseInt(text.getText().toString().trim());

        Intent intent=new Intent(MainActivity.this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this.getApplicationContext(), 234324243,intent,0);


        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (i * 1000), pendingIntent);

        Toast.makeText(this, "Alarm is set "+i+" seconds", Toast.LENGTH_SHORT).show();

    }
}
