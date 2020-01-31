package com.peter.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText username,password;
    private Button loginBtn;
    private TextView createAccountText;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        username =findViewById(R.id.username_input);
        password=findViewById(R.id.password_input);
        loginBtn=findViewById(R.id.login_Button);
        createAccountText=findViewById(R.id.dont_have_account);


        createAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendUserToRegisterActivity();
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mUsername= username.getText().toString().trim();
                String mPassword=password.getText().toString().trim();

                if (!mUsername.isEmpty()){

                    if (!mPassword.isEmpty()){

                        nowLogin(mUsername,mPassword);

                    }
                    else {
                        password.setError("Required");
                        password.requestFocus();
                    }

                }
                else {
                    username.setError("Required");
                    username.requestFocus();
                }
            }
        });
    }


    private void nowLogin(String mUsername, String mPassword) {

        mAuth.signInWithEmailAndPassword(mUsername,mPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, StudentsListActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error=e.getMessage();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();

            }
        });
    }


    private void sendUserToRegisterActivity() {

        Intent intent = new Intent(MainActivity.this, AdminRegisterActivity.class);
        startActivity(intent);

    }



}
