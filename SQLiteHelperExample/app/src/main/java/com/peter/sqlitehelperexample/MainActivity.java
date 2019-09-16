package com.peter.sqlitehelperexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.peter.sqlitehelperexample.DataBaseHandler.Contact;
import com.peter.sqlitehelperexample.DataBaseHandler.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler dbHandler=new DBHandler(this);
        //inserting contacts
        Log.d("Insert","Insertind data...");
        dbHandler.addContact(new Contact("Peter","0111222233"));
        dbHandler.addContact(new Contact("Kelly","35355555222"));
        dbHandler.addContact(new Contact("Fred","asjasvg3535535"));
        dbHandler.addContact(new Contact("Vinnie","xb24218db256v"));
        //Reading all contacts
        Log.d("Reading","reading all contacts...");
        List<Contact>contacts=dbHandler.getAllContacts();
        for (Contact cn:contacts){
            String log="id :"+cn.getId()+"Name :"+cn.getName()+"Phone Number :"+cn.getPhoneNumber();

            //Writing contacts to log
            Log.d("Name",log);
        }


    }
}
