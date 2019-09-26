package com.peter.callstateexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        final PhoneStateListener callStateListener=new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {

                if (state == TelephonyManager.CALL_STATE_RINGING){
                    Toast.makeText(getApplicationContext(), "Phone is Ringing...", Toast.LENGTH_LONG).show();

                }
                if (state == TelephonyManager.CALL_STATE_OFFHOOK){

                    Toast.makeText(getApplicationContext(), "Phone is currently in a call", Toast.LENGTH_LONG).show();
                }
                if (state == TelephonyManager.CALL_STATE_IDLE){
                    Toast.makeText(getApplicationContext(), "Phone is neither ringing nor in a call", Toast.LENGTH_LONG).show();
                }

            }
        };
        telephonyManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);
    }
}
