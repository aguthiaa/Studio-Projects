package com.peter.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentsListActivity extends AppCompatActivity {
    private TextView studentList;
    private Button addStudent;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        mRef = FirebaseDatabase.getInstance().getReference().child("Students").child(currentUserID);

        studentList= (TextView) findViewById(R.id.students_list);
        addStudent= (Button) findViewById(R.id.add_student_btn);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendUserToHomeActivity();

            }


        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists());
                if (dataSnapshot.hasChild("fullName"))
                {
                    String name =dataSnapshot.child("fullName").getValue().toString();
                    studentList.setText("Name "+name);
                }
                if (dataSnapshot.hasChild("regNo"))
                {
                    String reg=dataSnapshot.child("regNo").getValue().toString();
                    studentList.setText(reg);

                }
                if (dataSnapshot.hasChild("unitName"))
                {
                    String unit_name=dataSnapshot.child("unitName").getValue().toString();
                    studentList.setText(unit_name);
                }
                if (dataSnapshot.hasChild("unitCode"))
                {
                    String unit_code=dataSnapshot.child("unitCode").getValue().toString();
                    studentList.setText(unit_code);
                }
                if (dataSnapshot.hasChild("unitMarks"))
                {
                    String unit_marks=dataSnapshot.child("unitMarks").getValue().toString();
                    studentList.setText(unit_marks);
                }
                if (dataSnapshot.hasChild("unitGrade"))
                {
                    String unit_grade=dataSnapshot.child("unitGrade").getValue().toString();
                    studentList.setText(unit_grade);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendUserToHomeActivity() {
        Intent intent = new Intent(StudentsListActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if (currentUser == null)
        {
            Intent intent =new Intent(StudentsListActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
