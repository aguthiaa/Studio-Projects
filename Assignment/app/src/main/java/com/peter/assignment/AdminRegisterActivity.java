package com.peter.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class AdminRegisterActivity extends AppCompatActivity {
    private EditText mEmail,mName,mPassword,mConfirmPassword;
    private Button registerBtn;

    FirebaseAuth mAuth;
    DatabaseReference mRef;

    private String name;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        mAuth= FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference().child("Admin");

        initializeViews();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyUserInputs();
            }
        });
    }



    private void verifyUserInputs() {

        String email=mEmail.getText().toString().trim();
        name=mName.getText().toString().trim();
        String password=mPassword.getText().toString().trim();
        String confirmPassword=mConfirmPassword.getText().toString().trim();

        if (!email.isEmpty())
        {
            if (!name.isEmpty())
            {

                if (!password.isEmpty())
                {

                    if (!confirmPassword.isEmpty())
                    {

                        if (password.equals(confirmPassword))
                        {
                            doRegister(email,password);

                        }
                        else
                        {
                            Toast.makeText(AdminRegisterActivity.this, "Password Mismatch", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        mConfirmPassword.setError("Required");
                        mConfirmPassword.requestFocus();
                    }

                }
                else
                {
                    mPassword.setError("Required");
                    mPassword.requestFocus();
                }

            }
            else
            {
                mName.setError("Required");
                mName.requestFocus();
            }

        }
        else
        {
            mEmail.setError("Required");
            mEmail.requestFocus();
        }
    }



    private void doRegister(final String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(AdminRegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            currentUserID=mAuth.getCurrentUser().getUid();

                            HashMap hashMap=new HashMap();
                            hashMap.put("email",email);
                            hashMap.put("name",name);

                            mRef.child(currentUserID).updateChildren(hashMap)
                                    .addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {

                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(AdminRegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                                Intent intent =new Intent(AdminRegisterActivity.this,MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                fileList();
                                            }

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    String error = e.getMessage();
                                    Toast.makeText(AdminRegisterActivity.this, error, Toast.LENGTH_LONG).show();

                                }
                            });

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }





    private void initializeViews() {

        mEmail=findViewById(R.id.admin_email);
        mName=findViewById(R.id.admin_name);
        mPassword=findViewById(R.id.admin_password);
        mConfirmPassword=findViewById(R.id.admin_confirm_password);
        registerBtn=findViewById(R.id.register_btn);
    }


}
