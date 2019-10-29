 package com.peter.socialnetworkingapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

 public class ProfileActivity extends AppCompatActivity {

    private CircleImageView profile_image;
    private TextView profile_name,profile_user_name,profile_status,profile_country,profile_dob,profile_gender,profile_relationship_status;

    private Toolbar mToolbar;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mToolbar=(Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        mAuth=FirebaseAuth.getInstance();
        currentUserID=mAuth.getCurrentUser().getUid();
        userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    if (dataSnapshot.hasChild("profileImage")){

                        String image=dataSnapshot.child("profileImage").getValue().toString();

                        Picasso.get().load(image).placeholder(R.drawable.profile).into(profile_image);
                    }

                    if (dataSnapshot.hasChild("fullname")){

                        String fullname=dataSnapshot.child("fullname").getValue().toString();
                        profile_name.setText(""+fullname);
                    }
                    if (dataSnapshot.hasChild("username")){

                        String username=dataSnapshot.child("username").getValue().toString();

                        profile_user_name.setText("@"+username);
                    }
                    if (dataSnapshot.hasChild("status")){

                        String status=dataSnapshot.child("status").getValue().toString();

                        profile_status.setText(status);
                    }
                    if (dataSnapshot.hasChild("country")){

                       String country=dataSnapshot.child("country").getValue().toString();
                       profile_country.setText("Country: "+country);
                    }
                    if (dataSnapshot.hasChild("dob")){

                        String dob=dataSnapshot.child("dob").getValue().toString();
                        profile_dob.setText("DOB: "+dob);
                    }
                    if (dataSnapshot.hasChild("gender")){
                        String gender=dataSnapshot.child("gender").getValue().toString();

                        profile_gender.setText("Gender: "+gender);
                    }
                    if (dataSnapshot.hasChild("relationship_status")){
                        String relationship=dataSnapshot.child("relationship_status").getValue().toString();
                        profile_relationship_status.setText("Relationship: "+relationship);
                    }


                }
                else {

                    //Nothing To do...
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



     private void initViews() {
        profile_image=(CircleImageView) findViewById(R.id.profile_profile_image);
        profile_name=(TextView) findViewById(R.id.profile_name_textView);
        profile_user_name=(TextView) findViewById(R.id.profile_user_name_textView);
        profile_status=(TextView) findViewById(R.id.profile_status_textView);
        profile_country=(TextView) findViewById(R.id.profile_country_textView);
        profile_dob=(TextView) findViewById(R.id.profile_date_of_birth_textView);
        profile_gender=(TextView) findViewById(R.id.profile_gender_textView);
        profile_relationship_status=(TextView) findViewById(R.id.profile_relationship_status_textView);
     }


 }
