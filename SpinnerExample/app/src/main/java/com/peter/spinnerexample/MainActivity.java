package com.peter.spinnerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.peter.spinnerexample.databases.DatabaseHandler;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText addItem;
    private Button buttonAdd;
    private Spinner spinner;
    private TextView viewDataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeViews();
        spinner.setOnItemSelectedListener(this);
        
        //loading spinner data from database
        //loadSpinnerData(label);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String label=addItem.getText().toString().trim();

                if (label.length()>0){
                    DatabaseHandler db=new DatabaseHandler(getApplicationContext());
                    db.insertLabel(label);
                    //making input field text to blank
                    addItem.setText("");
                    //Hiding the keyboard

                    InputMethodManager imm=(InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(addItem.getWindowToken(),0);

//                    Toast.makeText(MainActivity.this, db.getAllLabels(), Toast.LENGTH_LONG).show();
                    viewDataItems.setText(db.getAllLabels());
                    //spinner.setAdapter(db.getAllLabels());

                    //loading spinner with newly added data

//                    loadSpinnerData(label);


                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter a label", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //function to load spinner data from sqlite database
  //  private void loadSpinnerData(String label) {

//        DatabaseHandler db=new DatabaseHandler(getApplicationContext());
//        List<String>labels=db.getAllLabels();
//        //creatind adapter for spinner
//        ArrayAdapter<String>dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,labels);
//        //Drop down layout style-list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //attaching data adapter to spinner.
//        //attaching data adapter to spinner
//        spinner.setAdapter(dataAdapter);


 //   }


    private void intializeViews() {
        addItem=findViewById(R.id.editTextAddItem);
        buttonAdd=findViewById(R.id.buttonAdd);
        spinner=findViewById(R.id.spinnerItem);
        viewDataItems=findViewById(R.id.viewData);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String label=adapterView.getItemAtPosition(position).toString();

        //showing selected spinner item
        Toast.makeText(getApplicationContext(), "You selected : "+label, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
