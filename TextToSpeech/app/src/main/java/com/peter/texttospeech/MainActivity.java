package com.peter.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private EditText text;
    private Button speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech=new TextToSpeech(this,this);
        text=findViewById(R.id.editText1);
        speak=findViewById(R.id.button1);

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                
                speakOut();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS){
            int result=textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){

                Toast.makeText(this, "Language not supported", Toast.LENGTH_LONG).show();

            }
            else {
                speak.setEnabled(true);
                speakOut();
            }

        }

    }

    private void speakOut() {

        String inputText=text.getText().toString().trim();
        textToSpeech.speak(inputText,TextToSpeech.QUEUE_FLUSH,null);
    }

}
