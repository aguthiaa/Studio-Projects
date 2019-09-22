package com.peter.contextmenuexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    String contacts[]={"Peter","Osteen","Patrick","Evans","Edwin","KJay","Letina","Nicole"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.myListView);
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(arrayAdapter);
        //Register listview in context menu
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        menu.setHeaderTitle("Select Action");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.call){
            Toast.makeText(getApplicationContext(), "Calling code", Toast.LENGTH_LONG).show();
        }
        else if (item.getItemId()==R.id.sms){
            Toast.makeText(getApplicationContext(), "Sending sms", Toast.LENGTH_LONG).show();
        }
        else {
            return false;
        }
        return true;
    }
}
