package com.peter.simplelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView simpleListView;
    String [] commonIconsNames = {"Like","Comment","Facebook","Twitter","Google"};
    int [] iconImages ={R.drawable.like,R.drawable.comment,R.drawable.facebook, R.drawable.twitter,R.drawable.google};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
