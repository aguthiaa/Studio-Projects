package com.peter.ecommerce1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.peter.ecommerce1.Prevalent.Prevalent;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity
{

    private CircleImageView userProfileImage;
    private EditText mPhoneNumber, mFullName,mAddress;
    private TextView changeProfile, closeSettings, updateSettings;

    private Uri imageUri;
    private String profileUrl = "";
    private StorageReference profilePicturesRef;
    private String checker="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initViews();

        profilePicturesRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
    }

    private void initViews()
    {
        userProfileImage = (CircleImageView) findViewById(R.id.settings_profile_image);
        mPhoneNumber = (EditText) findViewById(R.id.settings_phone_number);
        mFullName = (EditText) findViewById(R.id.settings_full_name);
        mAddress = (EditText) findViewById(R.id.settings_address);
        changeProfile = (TextView) findViewById(R.id.settings_profile_text);
        closeSettings = (TextView) findViewById(R.id.settings_close);
        updateSettings = (TextView) findViewById(R.id.settings_update);


        userInfoDisplay(userProfileImage,mPhoneNumber,mFullName,mAddress);

        closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        updateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateOnlyUserInfo();
                }
            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checker = "clicked";

                CropImage.activity(imageUri)
                        .setAspectRatio(1,1)
                        .start(SettingsActivity.this);

            }
        });
    }



    private void updateOnlyUserInfo()
    {
    }

    private void userInfoSaved()
    {
    }

    private void userInfoDisplay(final CircleImageView userProfileImage, final EditText mPhoneNumber, final EditText mFullName, final EditText mAddress)
    {

        DatabaseReference userRef;
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhoneNumber());


        userRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    if (dataSnapshot.child("profileImage").exists())
                    {
                        String image = dataSnapshot.child("profileImage").getValue().toString();
                        String name = dataSnapshot.child("fullName").getValue().toString();
                        String number = dataSnapshot.child("profileNumber").getValue().toString();
                        String address = dataSnapshot.child("email").getValue().toString();


                        Picasso.get().load(image).into(userProfileImage);
                        mPhoneNumber.setText(number);
                        mFullName.setText(name);
                        mAddress.setText(address);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
