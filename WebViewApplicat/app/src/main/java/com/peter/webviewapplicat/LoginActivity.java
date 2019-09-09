package com.peter.webviewapplicat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private EditText email,password;
    private Button login;
    private TextView signUp;

    ProgressDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDialog=new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        initializeViews();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });
    }

    private void verify() {
        myDialog.setTitle("Login");
        myDialog.setMessage("Please wait...");
        myDialog.show();
        String mEmail=email.getText().toString().trim();
        String mPassword=password.getText().toString().trim();

        if (!mEmail.isEmpty()){
            if (!mPassword.isEmpty()){

                doLogin(mEmail,mPassword);
            }
            else {
                myDialog.dismiss();
                password.setError("Password is required");
                password.requestFocus();
            }

        }
        else {
            myDialog.dismiss();
            email.setError("Email is required");
            email.requestFocus();
        }
    }

    private void doLogin(String mEmail, String mPassword) {

        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            user = mAuth.getCurrentUser();
                            myDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            myDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                myDialog.dismiss();
                String error=e.getMessage();
                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeViews() {
        email=findViewById(R.id.editTextEmailLogin);
        password=findViewById(R.id.editTextPasswordLogin);
        login=findViewById(R.id.loginButton);
        signUp=findViewById(R.id.loginTextView);
    }
}
