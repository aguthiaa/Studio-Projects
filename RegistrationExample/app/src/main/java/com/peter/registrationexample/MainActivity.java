package com.peter.registrationexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference userRef;


    EditText editTextFirstName,editTextEmail,editTextPassword;
    Button regButton;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth=FirebaseAuth.getInstance();

        //Referencing a tables
        userRef= FirebaseDatabase.getInstance().getReference("users");

        mDialog=new ProgressDialog(this);
        initializeViews();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });



    }
    private void initializeViews() {
        editTextFirstName=findViewById(R.id.first_Name);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        regButton=findViewById(R.id.registerButton);
    }

    private void register() {

        mDialog.setTitle("Register");
        mDialog.setMessage("Please wait...");
        mDialog.show();

        String fname=editTextFirstName.getText().toString().trim();
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();


        if (!fname.isEmpty()){

                if (!email.isEmpty()){
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){

                        if (!password.isEmpty()){
                            if (password.length() >= 6){
                                doRegister(fname,email,password);

                            }
                            else {
                                mDialog.dismiss();
                                Toast.makeText(this, "Password should be atleast six characters Long", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            mDialog.dismiss();
                            editTextPassword.setError("Password is required");
                            editTextPassword.requestFocus();
                        }

                    }
                    else {
                        mDialog.dismiss();
                        Toast.makeText(this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    mDialog.dismiss();
                    editTextEmail.setError("Error is required");
                    editTextEmail.requestFocus();
                }

        }
        else {
            mDialog.dismiss();
            editTextFirstName.setError("First Name is required");
            editTextFirstName.requestFocus();

        }
    }

    private void doRegister(final String fname, final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){


                            String userId= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                            HashMap<String,Object> hashMap=new HashMap<>();
                            hashMap.put("userId", userId);
                            hashMap.put("name",fname);
                            hashMap.put("email", email);
                            userRef.child(userId).setValue(hashMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                mDialog.dismiss();
                                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else {
                                                mDialog.dismiss();
                                                Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    String error=e.getMessage();
                                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();

                                }
                            });

                        }else {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Error. Try Again", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error=e.getMessage();
                mDialog.dismiss();
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
