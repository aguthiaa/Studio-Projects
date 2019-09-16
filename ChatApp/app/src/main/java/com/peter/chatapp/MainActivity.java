package com.peter.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private ListView listViewOfChatRoomsName;
    private EditText chatRoomName;
    private Button addRoomButton;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listOfChatRooms=new ArrayList<>();
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfChatRooms);
        listViewOfChatRoomsName.setAdapter(arrayAdapter);

        requestUserName();

        addRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myReference=database.getReference(chatRoomName.getText().toString());
                myReference.setValue("");


                chatRoomName.setText("");
                chatRoomName.requestFocus();
            }
        });


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myReference=database.getReference(chatRoomName.getText().toString());
        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set=new HashSet<String>();
                Iterator iterator=dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                listOfChatRooms.clear();
                listOfChatRooms.addAll(set);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewOfChatRoomsName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent messages =new Intent(MainActivity.this, MessageActivity.class);
                messages.putExtra("user_name", name);
                messages.putExtra("room_name", ((TextView)view).getText().toString());
                startActivity(messages);

            }
        });

    }

    private void requestUserName() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Enter UserName");
        final EditText inputField=new EditText(this);
        builder.setView(inputField);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name=inputField.getText().toString().trim();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

                requestUserName();

            }
        });
        builder.show();
    }

    private void initViews() {
        listViewOfChatRoomsName =findViewById(R.id.chatRoomView);
        chatRoomName =findViewById(R.id.chatRoomEditText);
        addRoomButton =findViewById(R.id.createChatRoomButton);
    }
}
