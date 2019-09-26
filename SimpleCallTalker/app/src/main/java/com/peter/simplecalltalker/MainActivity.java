package com.peter.simplecalltalker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech=new TextToSpeech(this,this);

        TelephonyManager telephonyManager=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        final PhoneStateListener callStateListener=new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                if (state == TelephonyManager.CALL_STATE_RINGING){
                    Toast.makeText(MainActivity.this, "Phone Ringing", Toast.LENGTH_SHORT).show();
                }

                if (state == TelephonyManager.CALL_STATE_OFFHOOK){
                    Toast.makeText(MainActivity.this, "Phone is in a call", Toast.LENGTH_SHORT).show();
                }
                if (state == TelephonyManager.CALL_STATE_IDLE){
                    Toast.makeText(MainActivity.this, "Phone is neither ringing nor in a call", Toast.LENGTH_SHORT).show();
                }
            }
        };
        telephonyManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){


            int result=textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
            }
            else {

            }
        }
        else {
            Toast.makeText(this, "Initialization Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {

        if (textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
