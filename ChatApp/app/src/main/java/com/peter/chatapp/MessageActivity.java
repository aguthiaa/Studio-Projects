package com.peter.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;

public class MessageActivity extends AppCompatActivity {
    private ImageButton sendMessage;
    private TextView displayMessage;
    private EditText inputMessage;
    private String user_name, room_name;
    private DatabaseReference root;
    private String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        initializeViews();

        room_name=getIntent().getExtras().get("room_name").toString();
        user_name=getIntent().getExtras().get("user_name").toString();
        setTitle("Room "+room_name);
        root= FirebaseDatabase.getInstance().getReference().child(room_name);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap=new HashMap<String, Object>();
                temp_key=root.push().getKey();
                root.updateChildren(hashMap);

                DatabaseReference userRef=root.child(temp_key);
                HashMap<String, Object> userMessage=new HashMap<String, Object>();
                userMessage.put("name", user_name);
                userMessage.put("message", inputMessage.getText().toString().trim());
                userRef.updateChildren(userMessage);

                inputMessage.setText("");
                inputMessage.requestFocus();

            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                
                appendChatConversation(dataSnapshot);
                
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                appendChatConversation(dataSnapshot);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    String chatMessage, chatUserName;

    private void appendChatConversation(DataSnapshot dataSnapshot) {

        Iterator iterator=dataSnapshot.getChildren().iterator();

        while (iterator.hasNext()){
            chatMessage=(String) ((DataSnapshot)iterator.next()).getValue();
            chatUserName=(String) ((DataSnapshot)iterator.next()).getValue();
            displayMessage.append(chatUserName+" : "+chatMessage+"\n\n");
        }

    }

    private void initializeViews() {
        sendMessage=findViewById(R.id.sendMessageButton);
        displayMessage=findViewById(R.id.messageTextView);
        inputMessage=findViewById(R.id.messageEditText);

    }
}
