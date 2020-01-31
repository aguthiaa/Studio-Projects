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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private EditText mName, mRegNo, unitName, unitCode,unitMarks,unitGrade;
    private Button addNewStudent;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth =FirebaseAuth.getInstance();
        //currentUserID=mAuth.getCurrentUser().getUid();
        mRef= FirebaseDatabase.getInstance().getReference().child("Students");

        initializeViews();


        addNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyInputs();

            }
        });



    }



    private void verifyInputs() {

        String name=mName.getText().toString().trim();
        String regNo=mRegNo.getText().toString().trim();
        String unit_name=unitName.getText().toString().trim();
        String unit_code=unitCode.getText().toString().trim();
        String unit_marks=unitMarks.getText().toString().trim();
        String unit_grade=unitGrade.getText().toString().trim();

        if (!name.isEmpty())
        {
            if (!regNo.isEmpty())
            {
                if (!unit_name.isEmpty())
                {
                    if (!unit_code.isEmpty())
                    {
                        if (!unit_marks.isEmpty())
                        {
                            if (!unit_grade.isEmpty())
                            {
                               nowAddStudent(name,regNo,unit_name,unit_code,unit_marks,unit_grade);
                            }
                            else
                            {
                                unitGrade.setError("Required");
                                unitGrade.requestFocus();
                            }
                        }
                        else
                        {
                            unitMarks.setError("Required");
                            unitMarks.requestFocus();
                        }
                    }
                    else
                    {
                        unitCode.setError("Required");
                        unitCode.requestFocus();
                    }
                }
                else
                {
                    unitName.setError("Required");
                    unitName.requestFocus();
                }
            }
            else
            {
                mRegNo.setError("Required");
                mRegNo.requestFocus();
            }
        }
        else
        {
            mName.setError("Required");
            mName.requestFocus();
        }
    }

    private void nowAddStudent(String name, String regNo, String unit_name, String unit_code, String unit_marks, String unit_grade) {

        currentUserID=mAuth.getCurrentUser().getUid();

        HashMap hashMap =new HashMap();
        hashMap.put("fullName",name);
        hashMap.put("regNo",regNo);
        hashMap.put("unitName",unit_name);
        hashMap.put("unitCode",unit_code);
        hashMap.put("unitMarks",unit_marks);
        hashMap.put("unitGrade",unit_grade);

        mRef.child(currentUserID).updateChildren(hashMap)
                .addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(HomeActivity.this, "New Student Added Successfully", Toast.LENGTH_LONG).show();

                    sendUserToStudentListActivity();
                }

                else {
                    Toast.makeText(HomeActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String error = e.getMessage();

                Toast.makeText(HomeActivity.this, error, Toast.LENGTH_LONG).show();

            }
        });
    }



    private void sendUserToStudentListActivity() {

        Intent intent = new Intent(HomeActivity.this,StudentsListActivity.class);
        startActivity(intent);
    }




    private void initializeViews() {
        mName =(EditText) findViewById(R.id.student_name);
        mRegNo =(EditText) findViewById(R.id.reg_number);
        unitName=(EditText) findViewById(R.id.unit_name);
        unitCode =(EditText) findViewById(R.id.unit_code);
        unitMarks =(EditText) findViewById(R.id.unit_marks);
        unitGrade =(EditText) findViewById(R.id.unit_grade);
        addNewStudent =(Button) findViewById(R.id.submit);
    }

}
