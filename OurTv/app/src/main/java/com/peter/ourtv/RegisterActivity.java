package com.peter.ourtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference myRef;

    ProgressDialog myDialog;
    private EditText fname, lname, email, password, password2;
    private TextView loginText;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("users");

        myDialog = new ProgressDialog(this);
        intializeViews();
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void register() {

        myDialog.setTitle("Register.");
        myDialog.setMessage("Please wait...");
        myDialog.show();
        String mName = fname.getText().toString().trim();
        String mName2 = lname.getText().toString().trim();
        String mEmail = email.getText().toString().trim();
        String passcode = password.getText().toString().trim();
        String passcode2 = password2.getText().toString().trim();

        if (!mName.isEmpty()) {
            if (!mName2.isEmpty()) {
                if (!mEmail.isEmpty()) {
                    if (!passcode.isEmpty()) {
                        if (passcode.length() >= 6) {
                            if (!passcode2.isEmpty()) {
                                if (passcode.equals(passcode2)) {

                                    doRegister(mName, mName2, mEmail, passcode);

                                } else {
                                    myDialog.dismiss();
                                    Toast.makeText(this, "Password Mismatch!!!", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                myDialog.dismiss();
                                password2.setError("This field is required");
                                password2.requestFocus();
                            }

                        } else {
                            myDialog.dismiss();
                            password.setError("Must be 6 or more characters long");
                            password.requestFocus();
                        }

                    } else {
                        myDialog.dismiss();
                        password.setError("Password is required");
                        password.requestFocus();
                    }

                } else {
                    myDialog.dismiss();
                    email.setError("Email is required");
                    email.requestFocus();
                }

            } else {
                myDialog.dismiss();
                lname.setError("LastName is required");
                lname.requestFocus();
            }

        } else {
            myDialog.dismiss();
            fname.setError("FirstName cannot be Empty");
            fname.requestFocus();
        }
    }

    private void doRegister(final String mName, final String mName2, final String mEmail, String passcode) {

        mAuth.createUserWithEmailAndPassword(mEmail, passcode)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("userId", userId);
                            hashMap.put("firstName", mName);
                            hashMap.put("lastName", mName2);
                            hashMap.put("Email", mEmail);
                            myRef.child(userId).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user = mAuth.getCurrentUser();
                                        myDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                        sendVerificationEmail();

                                        // User is signed in
                                        // NOTE: this Activity should get onpen only when the user is not signed in, otherwise
                                        // the user will receive another verification email.
                                        if (user != null) {
                                            sendVerificationEmail();
                                            Toast.makeText(RegisterActivity.this, "Verification Link sent", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Error Sending verification link", Toast.LENGTH_LONG).show();
                                        }

//                                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
//                                        startActivity(intent);
//                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Try Again,An Error Occurred", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    String error = e.getMessage();
                                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error = e.getMessage();
                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void sendVerificationEmail() {
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // email sent


                                // after email is sent just logout the user and finish this activity
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // email not sent, so display message and restart the activity or do whatever you wish to do

                                //restart this activity
                                overridePendingTransition(0, 0);
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());

                            }
                        }
                    });
        }
    }

    private void intializeViews() {
        fname = findViewById(R.id.editTextFirstName);
        lname = findViewById(R.id.editTextLastName);
        email = findViewById(R.id.editTextEmailRegister);
        password = findViewById(R.id.editTextPasswordRegister);
        password2 = findViewById(R.id.editTextRe_PasswordRegister);
        loginText = findViewById(R.id.textViewRegister);
        regButton = findViewById(R.id.registerButton);
    }
}

