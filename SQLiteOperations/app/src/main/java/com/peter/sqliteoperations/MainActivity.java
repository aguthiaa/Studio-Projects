package com.peter.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Name,Pass,updateold,updatenew,delete;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        Name=(EditText)findViewById(R.id.userNameEditText);
        Pass=(EditText)findViewById(R.id.passwordEditText);
        updateold=(EditText)findViewById(R.id.currentNameEditText);
        updatenew=(EditText)findViewById(R.id.newNameEditText);
        delete=(EditText)findViewById(R.id.deleteNameEditText);
        helper=new myDbAdapter(this);
    }

    public void addUser(View view) {
        String t1=Name.getText().toString().trim();
        String t2=Pass.getText().toString().trim();

        if (t1.isEmpty() || t2.isEmpty()){
            Toast.makeText(this, "Name or Password cannot be empty", Toast.LENGTH_LONG).show();
        }
        else {
            long id=helper.insertData(t1,t2);
            if (id <= 0){
                Toast.makeText(getApplicationContext(), "Insertion unsuccessful", Toast.LENGTH_LONG).show();
                Name.setText("");
                Pass.setText("");
            }
            else {

                Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_LONG).show();
                Name.setText("");
                Pass.setText("");

            }
        }
    }

    public void viewdata(View view) {
        String data=helper.getData();
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    public void update(View view) {
      String u1=updateold.getText().toString().trim();
      String u2=updatenew.getText().toString().trim();

      if (u1.isEmpty() || u2.isEmpty()){
          Toast.makeText(getApplicationContext(), "Current name and New name cannot be empty", Toast.LENGTH_LONG).show();
      }
      else {
          int a=helper.updateName(u1,u2);
          if (a <= 0){
              Toast.makeText(getApplicationContext(), "Update unsuccessful", Toast.LENGTH_LONG).show();
              updateold.setText("");
              updatenew.setText("");
          }
          else {
              Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_LONG).show();
              updateold.setText("");
              updatenew.setText("");
          }
      }
    }
    public void delete(View view) {
        String uname=delete.getText().toString().trim();
        if (uname.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Data to delete", Toast.LENGTH_LONG).show();
        }
        else {
            int a=helper.delete(uname);
            if (a <= 0){
                Toast.makeText(getApplicationContext(), "Delete Unsuccessful", Toast.LENGTH_LONG).show();
                delete.setText("");
            }
            else {
                Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                delete.setText("");
            }
        }
    }


}
