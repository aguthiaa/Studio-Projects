package com.peter.advancedchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
    private EditText chatRoomName;
    private Button addChatRoom;
    private ListView chatRoomsListView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listOfChatsRooms =new ArrayList<String>();
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listOfChatsRooms);
        chatRoomsListView.setAdapter(arrayAdapter);

        requestUserName();

        addChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room_name=chatRoomName.getText().toString().trim();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference mReference=database.getReference(room_name);
                mReference.setValue("");
            }
        });


        String room_name=chatRoomName.getText().toString().trim();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference mReference=database.getReference(room_name);
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set=new HashSet<String>();
                Iterator i=dataSnapshot.getChildren().iterator();
                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                listOfChatsRooms.clear();
                listOfChatsRooms.addAll(set);
                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void requestUserName() {
        //requesting user name through a dialog box
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
        chatRoomName=findViewById(R.id.roomNameEditText);
        addChatRoom=findViewById(R.id.addRoomButton);
        chatRoomsListView =findViewById(R.id.chatsListView);
    }
}
