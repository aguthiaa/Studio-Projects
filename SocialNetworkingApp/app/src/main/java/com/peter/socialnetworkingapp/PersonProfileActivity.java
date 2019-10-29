package com.peter.socialnetworkingapp;

import android.os.Bundle;
import android.widget.Button;
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

public class PersonProfileActivity extends AppCompatActivity {

    private CircleImageView person_profile;
    private TextView person_fullname,person_username,person_status,person_country,person_dob,person_gender,person_relationship_satus;
    private Button send_friend_request,decline_friend_request;

    private Toolbar mToolbar;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef,profileUsersRef;
    private String senderUserID,receiverUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);

        receiverUserID=getIntent().getExtras().get("visit_user_id").toString();

        mToolbar=(Toolbar) findViewById(R.id.person_profile_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Person Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth=FirebaseAuth.getInstance();
        senderUserID=mAuth.getCurrentUser().getUid();
        userRef= FirebaseDatabase.getInstance().getReference().child("Users");

        initViews();

        userRef.child(receiverUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    if (dataSnapshot.hasChild("profileImage")){

                        String image=dataSnapshot.child("profileImage").getValue().toString();

                        Picasso.get().load(image).placeholder(R.drawable.profile).into(person_profile);
                    }

                    if (dataSnapshot.hasChild("fullname")){

                        String mFullname=dataSnapshot.child("fullname").getValue().toString();
                        person_fullname.setText(mFullname);
                    }
                    if (dataSnapshot.hasChild("username")){

                        String mUsername=dataSnapshot.child("username").getValue().toString();
                        person_username.setText("@"+mUsername);
                    }
                    if (dataSnapshot.hasChild("status")){

                        String mStatus=dataSnapshot.child("status").getValue().toString();
                        person_status.setText(mStatus);
                    }
                    if (dataSnapshot.hasChild("country")){

                        String mCountry=dataSnapshot.child("country").getValue().toString();
                        person_country.setText("Country: "+mCountry);
                    }
                    if (dataSnapshot.hasChild("dob")){

                        String mDOB=dataSnapshot.child("dob").getValue().toString();
                        person_dob.setText("Date of birth: "+mDOB);
                    }
                    if (dataSnapshot.hasChild("gender")){

                        String mGender=dataSnapshot.child("gender").getValue().toString();
                        person_gender.setText("Gender: "+mGender);
                    }
                    if (dataSnapshot.hasChild("relationship_status")){

                        String mRelationship=dataSnapshot.child("relationship_status").getValue().toString();
                        person_relationship_satus.setText("Relationship: "+mRelationship);
                    }
                }

                else {

                    //To do...
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void initViews() {

        person_profile=(CircleImageView) findViewById(R.id.person_profile_image);
        person_fullname=(TextView) findViewById(R.id.person_profile_name);
        person_username=(TextView) findViewById(R.id.person_profile_user_name);
        person_status=(TextView) findViewById(R.id.person_profile_status);
        person_country=(TextView) findViewById(R.id.person_profile_country);
        person_dob=(TextView) findViewById(R.id.person_profile_date_of_birth);
        person_gender=(TextView) findViewById(R.id.person_profile_gender);
        person_relationship_satus=(TextView) findViewById(R.id.person_profile_relationship_status);
        send_friend_request=(Button) findViewById(R.id.person_profile_send_friend_request);
        decline_friend_request=(Button) findViewById(R.id.person_profile_decline_friend_request);
    }


}
