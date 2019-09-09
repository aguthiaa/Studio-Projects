package com.peter.ourtv;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ChannelActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        FloatingActionButton fabMail = findViewById(R.id.fabEmail1);
        FloatingActionButton fabShare = findViewById(R.id.fabShare1);
        FloatingActionButton fabText = findViewById(R.id.fabText);



        fabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Send sms action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Uri uriSms = Uri.parse("smsto:");
                Intent intentSMS = new Intent(Intent.ACTION_SENDTO, uriSms);
                intentSMS.putExtra("sms_body", "Dear Friend, Check out Tv Player to view Local and" +
                        " international Tv channels for free,Download using the link below");
                startActivity(intentSMS);
            }
        });



        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Share action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent mEmail=new Intent(Intent.ACTION_SEND);
                mEmail.setType("text/email");
                mEmail.putExtra(Intent.EXTRA_TEXT, "Dear Friend, Check out Tv Player to view\n local and international Tv" +
                        "\nchannels for free,Download using the link below\n" + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(mEmail, "Send Feedback:"));

            }
        });


        fabMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Email action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","aguthi.aa@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Friend, Check out Tv Player to view\n local and international Tv" +
                        "\nchannels for free,Download using the link below\n" + "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_Call){
            String phone = "";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }
        else if (id == R.id.action_Camera){

        }
        else if (id == R.id.action_Mpesa){
            PackageManager manager = getPackageManager();
            Intent intent =manager.getLaunchIntentForPackage("com.android.stk");
            if (intent != null)
                startActivity(intent);
        }
        else if (id == R.id.action_logout){
            Intent intent = new Intent(ChannelActivity.this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void dw(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","NvqKZHpKs-g");
        startActivity(intent);
    }

    public void nbc(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","tPeUHECNLKs");
        startActivity(intent);
    }

    public void k24(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","AdysK2T4Qe8");
        startActivity(intent);
        finish();
    }

    public void cna(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","JvZVnBn6zEI");
        startActivity(intent);
    }

    public void france24(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","0fKyrdQ15gs");
        startActivity(intent);
    }

    public void skyNews(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","siyW0GOBtbo");
        startActivity(intent);
    }

    public void aljazeera(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","jL8uDJJBjMA");
        startActivity(intent);
    }

    public void cnn(View view) {
        Intent intent=new Intent(ChannelActivity.this,MainActivity.class);
        intent.putExtra("channel","SPQZfJktrGs");
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            Intent intent=new Intent(ChannelActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

}
