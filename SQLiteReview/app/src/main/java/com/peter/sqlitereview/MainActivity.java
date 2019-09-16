package com.peter.sqlitereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Name,Pass,updateold,updatenew,delete;
    private TextView listView;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeViews();
    }

    private void intializeViews() {
        Name=(EditText)findViewById(R.id.usernameEditText);
        Pass=(EditText)findViewById(R.id.passwordEditText);
        updateold=(EditText)findViewById(R.id.currentNameEditText);
        updatenew=(EditText)findViewById(R.id.newNameEditText);
        delete=(EditText)findViewById(R.id.deleteNameEditText);
        listView=(TextView) findViewById(R.id.listDataView);
        helper=new myDbAdapter(this);
    }

    public void addUser(View view) {
        String t1=Name.getText().toString().trim();
        String t2= Pass.getText().toString().trim();
        if (t1.isEmpty() || t2.isEmpty()){
            Toast.makeText(getApplicationContext(), "Username or Password cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            long id=helper.insertData(t1,t2);
            if (id <= 0){
                Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                Name.setText("");
                Pass.setText("");
            }
            else {
                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void viewdata(View view) {
        String data=helper.getData();
        listView.setText(data);
        //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        String u1=updateold.getText().toString().trim();
        String u2=updatenew.getText().toString().trim();

        if (u1.isEmpty() || u2.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter current and new name", Toast.LENGTH_LONG).show();

        }
        else {
            int a=helper.updateName(u1,u2);
            if (a <= 0){
                Toast.makeText(getApplicationContext(), "Unsuccessfull", Toast.LENGTH_LONG).show();
                updateold.setText("");
                updatenew.setText("");
            }
            else {
                Toast.makeText(getApplicationContext(), "Update successfully", Toast.LENGTH_LONG).show();
                updateold.setText("");
                updatenew.setText("");
            }
        }
    }

    public void delete(View view) {
        String uname=delete.getText().toString().trim();
        if (uname.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter data", Toast.LENGTH_LONG).show();
        }
        else {
            int a=helper.delete(uname);
            if (a <= 0){
                Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_LONG).show();
                delete.setText("");
            }
            else {
                Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_LONG).show();
                delete.setText("");
            }
        }
    }
}
