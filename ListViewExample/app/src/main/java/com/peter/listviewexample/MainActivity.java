package com.peter.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    static final String[] FRUITS={"Apple","Avocado","Banana","Blueberry","Orange","Mango","Melon","Pepino","Guava","Peech","Pineapple"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       setListAdapter(new ArrayAdapter<String>(this,R.layout.activity_main,FRUITS));

        final ListView listView=getListView();
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),FRUITS[position], Toast.LENGTH_SHORT).show();

            }
        });
    }
}
