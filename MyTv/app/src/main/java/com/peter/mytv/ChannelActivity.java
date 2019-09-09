package com.peter.mytv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChannelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
    }

    public void ktn(View view) {

        Intent intent=new Intent(ChannelActivity.this, MainActivity.class);
        intent.putExtra("channel", "iyDabk1SFUE");
        startActivity(intent);

    }

    public void k24(View view) {
        Intent intent=new Intent(ChannelActivity.this, MainActivity.class);
        intent.putExtra("channel", "AdysK2T4Qe8");
        startActivity(intent);
    }

    public void aljazeera(View view) {
        Intent intent=new Intent(ChannelActivity.this, MainActivity.class);
        intent.putExtra("channel", "jL8uDJJBjMA");
        startActivity(intent);
    }

    public void cnn(View view) {
        Intent intent=new Intent(ChannelActivity.this, MainActivity.class);
        intent.putExtra("channel", "rF0qJ8nhs8o");
        startActivity(intent);
    }
}
