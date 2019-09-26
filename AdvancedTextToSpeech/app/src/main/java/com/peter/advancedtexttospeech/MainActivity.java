package com.peter.advancedtexttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, AdapterView.OnItemSelectedListener {
    private TextToSpeech textToSpeech;
    private EditText text;
    private Button speak;
    private Spinner speedSpinner;

    private static String speed="Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textToSpeech=new TextToSpeech(this,this);
        text=findViewById(R.id.editText1);
        speak=findViewById(R.id.button1);
        speedSpinner =findViewById(R.id.spinner1);

        //Loading spinner Data.
        loadSpinnerData();
        speedSpinner.setOnItemSelectedListener(this);

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
                setSpeed();
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
        else {
            Toast.makeText(this, "Initialization failed", Toast.LENGTH_LONG).show();
        }

    }


    private void loadSpinnerData() {

        //Data for speed spinner

        List<String> labels=new ArrayList<String>();
        labels.add("Very Fast");
        labels.add("Fast");
        labels.add("Normal");
        labels.add("Slow");
        labels.add("Very Slow");


        //Creating adapter for spinner
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,labels);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        speedSpinner.setAdapter(arrayAdapter);
    }

    private void speakOut() {

        String inputText=text.getText().toString().trim();
        textToSpeech.speak(inputText,TextToSpeech.QUEUE_FLUSH,null);
    }

    private void setSpeed() {

        if (speed.equals("Very Slow")){
            textToSpeech.setSpeechRate(0.1f);

        }
        if (speed.equals("Slow")){
            textToSpeech.setSpeechRate(0.5f);
        }
        if (speed.equals("Normal")){
            textToSpeech.setSpeechRate(1.0f);//default speech rate
        }
        if (speed.equals("Fast")){
            textToSpeech.setSpeechRate(1.5f);
        }
        if (speed.equals("Very Fast")){
            textToSpeech.setSpeechRate(4.0f);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        speed=adapterView.getItemAtPosition(position).toString();

        Toast.makeText(adapterView.getContext(), "You selected : "+speed, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
