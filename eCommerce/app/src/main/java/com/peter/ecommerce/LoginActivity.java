package com.peter.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.peter.ecommerce.Model.Users;
import com.peter.ecommerce.Prevalent.Prevalent;

import java.util.Objects;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private TextView adminLink, notAdminLink,toRegisterLink;
    private EditText phoneNumber,password;
    private Button loginBtn;
    private CheckBox rememberME;


    private String parentDbName = "Users";
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        Paper.init(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyInputs();
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginBtn.setText("Login Admin");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });

        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setText("Login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });

        toRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToRegisterActivity();

            }
        });


    }




    private void verifyInputs() {

        mDialog.setTitle("Login");
        mDialog.setMessage("Please wait...");
        mDialog.show();

        String number=phoneNumber.getText().toString().trim();
        String passcode=password.getText().toString().trim();

        if (!number.isEmpty())
        {
            if (!passcode.isEmpty())
            {
                loginUser(number,passcode);
            }
            else
            {
                mDialog.dismiss();
                password.setError("Password is Required");
                password.requestFocus();
            }

        }
        else
        {
            mDialog.dismiss();
            phoneNumber.setError("Phone Number is Required");
            phoneNumber.requestFocus();
        }
    }



    private void loginUser(final String number, final String passcode) {

        if (rememberME.isChecked())
        {
            Paper.book().write(Prevalent.userPhoneKey,number);
            Paper.book().write(Prevalent.userPasswordKey,passcode);
        }

        final DatabaseReference rootRef;
        rootRef= FirebaseDatabase.getInstance().getReference();

        //Checking whether the user exists or not

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentDbName).child(number).exists())
                {

                    Users usersData=dataSnapshot.child(parentDbName).child(number).getValue(Users.class);

                    if (Objects.requireNonNull(usersData).getPhoneNumber().equals(number))
                    {
                        if (usersData.getPassword().equals(passcode))
                        {
                            if (parentDbName.equals("Admins"))
                            {
                                mDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                sendUserToAdminCategoryActivity();

                                Prevalent.currentOnlineUser = usersData;
                            }

                            else if (parentDbName.equals("Users"))
                            {
                                mDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                sendUserToHomeActivity();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is Incorrect", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                else
                {
                    mDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Account with this phone number: "+number+" does not exist", Toast.LENGTH_LONG).show();
                    Toast.makeText(LoginActivity.this, "You need to create a new account", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendUserToRegisterActivity() {

        Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void sendUserToAdminCategoryActivity() {
        Intent intent = new Intent(LoginActivity.this, AdminCategoryActivity.class);
        startActivity(intent);
    }

    private void sendUserToHomeActivity() {
        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }


    private void initViews() {

        adminLink = (TextView) findViewById(R.id.admin_panel_link);
        notAdminLink =(TextView) findViewById(R.id.not_admin_panel_link);
        toRegisterLink = (TextView) findViewById(R.id.register_link);
        phoneNumber=(EditText) findViewById(R.id.login_phone_number_input);
        password=(EditText) findViewById(R.id.login_password_input);
        rememberME=(CheckBox) findViewById(R.id.remember_me_checkbox);
        loginBtn = (Button) findViewById(R.id.login_page_button);
        mDialog=new ProgressDialog(this);
    }
}
