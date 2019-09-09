package com.peter.registrationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth= FirebaseAuth.getInstance();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser mUser=mAuth.getCurrentUser();

        if (mUser == null){
            Intent intent=new Intent(Main2Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
