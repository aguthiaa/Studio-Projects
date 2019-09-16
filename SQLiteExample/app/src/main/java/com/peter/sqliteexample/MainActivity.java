package com.peter.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.peter.sqliteexample.databaseHandler.Contact;
import com.peter.sqliteexample.databaseHandler.DatabaseHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText name,phoneNumber;
    private Button newContact;
    private TextView contaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.nameEditText);
        phoneNumber=findViewById(R.id.phoneEditText);
        newContact=findViewById(R.id.addContact);
       contaList=findViewById(R.id.contactLists);

        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName,phone_number;
                inputName=name.getText().toString().trim();
                phone_number=phoneNumber.getText().toString().trim();
                add(inputName,phone_number);
            }
        });


        //Inserting contacts
//        Log.d("Insert","inserting...");
//        dbHandler.addContact(new Contact("Peter","+254713382967"));
//        dbHandler.addContact(new Contact("Fred","+254745750401"));
//        dbHandler.addContact(new Contact("Kelly","+254771882111"));
//        dbHandler.addContact(new Contact("Waga","+254787103672"));


        //Reading all contacts
//        Log.d("Reading","Reading all contacts...");




    }

    private void add(String inputName, String phone_number) {
        DatabaseHandler dbHandler=new DatabaseHandler(this);
        Contact con = new Contact(inputName,phone_number);
        dbHandler.addContact(con);
        if (con != null){
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            showContacts();
        }
        else {
            Toast.makeText(this, "Error inserting data", Toast.LENGTH_SHORT).show();
        }

    }

    void showContacts(){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<String> conta = db.getContacts();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.activity_main,conta);

        contaList.setText((CharSequence) dataAdapter);
    }

}
