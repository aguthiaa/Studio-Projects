package com.peter.listviewreview;

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
    static  final String[] FRUITS={"Apple","Blueberry","Orange","Pineapple","Mango","Peech","Pepino","Lemon","Papaya","Guava","Ovacado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<String>(this,R.layout.activity_main,FRUITS));
         ListView listView=getListView();
         listView.setTextFilterEnabled(true);
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                 Toast.makeText(MainActivity.this, FRUITS[position], Toast.LENGTH_SHORT).show();
             }
         });

    }
}
