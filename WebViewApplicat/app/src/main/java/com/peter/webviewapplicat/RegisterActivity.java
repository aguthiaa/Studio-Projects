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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    DatabaseReference myRef;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private EditText fname,lname,email,password,password2;
    private Button regButton;
    private TextView signInTextView;
    ProgressDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       myRef=FirebaseDatabase.getInstance().getReference("users");

        myDialog=new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        intializeViews();
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {

        myDialog.setTitle("Register");
        myDialog.setMessage("Please wait...");
        myDialog.show();
        String myFname=fname.getText().toString().trim();
        String myLame=lname.getText().toString().trim();
        String myEmail=email.getText().toString().trim();
        String myPassword=password.getText().toString().trim();
        String myPassword2=password2.getText().toString().trim();

        if (!myFname.isEmpty()){

            if (!myLame.isEmpty()){

                if (!myEmail.isEmpty()){
                    if (!myPassword.isEmpty()){
                        if (!myPassword2.isEmpty()){

                            if (myPassword.equals(myPassword2)){
                                doRegister(myFname,myLame,myEmail,myPassword);
                            }
                            else {
                                myDialog.dismiss();
                                Toast.makeText(this, "Password MisMatch!!!", Toast.LENGTH_LONG).show();
                            }

                        }
                        else {
                            myDialog.dismiss();
                            password2.setError("Password is required");
                            password2.requestFocus();
                        }

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
            else {
                myDialog.dismiss();
                lname.setError("LastName is required");
                lname.requestFocus();
            }
        }
        else {
            myDialog.dismiss();
            fname.setError("FirstName is required");
            fname.requestFocus();
        }
    }

    private void doRegister(final String myFname, final String myLame, final String myEmail, String myPassword) {

        mAuth.createUserWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                            HashMap<String, Object> hashMap=new HashMap<>();
                            hashMap.put("userId",userId);
                            hashMap.put("firstName",myFname);
                            hashMap.put("lastName", myLame);
                            hashMap.put("email", myEmail);

                            myRef.child(userId).setValue(hashMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        user = mAuth.getCurrentUser();
                                        myDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        myDialog.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Try Again. An error occurred!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                        } else {
                            myDialog.dismiss();
                            // If sign in fails, display a message to the user.

                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               String error=e.getMessage();
               myDialog.dismiss();
                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void intializeViews() {
        fname=findViewById(R.id.editTextFirstName);
        lname=findViewById(R.id.editTextLastName);
        email=findViewById(R.id.editTextRegEmail);
        password=findViewById(R.id.editTextRegPassword1);
        password2=findViewById(R.id.editTextRegPassword2);
        regButton=findViewById(R.id.registerButton);
        signInTextView=findViewById(R.id.registerTextView);
    }
}
