package com.peter.ourtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailInput;
    ProgressDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        myDialog=new ProgressDialog(this);

        emailInput=findViewById(R.id.emailEditText);
    }

    public void submit(View view) {
        String resetEmail=emailInput.getText().toString().trim();
        if (!resetEmail.isEmpty()){
            resetUserPassword(resetEmail);
        }
        else {
            emailInput.setError("Email is required");
            emailInput.requestFocus();
        }
    }

    private void resetUserPassword(String resetEmail) {
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        myDialog.setTitle("Password Reset");
        myDialog.setMessage("Please wait...");
        myDialog.show();

        mAuth.sendPasswordResetEmail(resetEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    myDialog.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this, "Reset password instructions has been sent to your email", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
                else {
                    myDialog.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this, "Try Again, Error Occurred", Toast.LENGTH_LONG).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error=e.getMessage();
                Toast.makeText(ForgotPasswordActivity.this, error, Toast.LENGTH_LONG).show();

            }
        });
    }
}
