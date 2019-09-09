package com.peter.intenttestapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton share;
    FloatingActionButton call;
    FloatingActionButton email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        share = findViewById(R.id.fabShare);
        call = findViewById(R.id.fabCall);
        email = findViewById(R.id.fabEmail);

        //share button action listener
        share.setOnClickListener(new View.OnClickListener() {
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

        //call button action listener
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Call action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Uri uriSms = Uri.parse("smsto:");
                Intent intentSMS = new Intent(Intent.ACTION_SENDTO, uriSms);
                intentSMS.putExtra("sms_body", "Dear Friend, Check out Tv Player to view Local and" +
                        " international Tv channels for free,Download using the link below");
                startActivity(intentSMS);

            }
        });

        //email button action listener
        email.setOnClickListener(new View.OnClickListener() {
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

        }
        else if (id == R.id.action_call){
            String phone = "";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }
        else if (id == R.id.action_camera){

        }
        else if (id == R.id.action_mpesa){

            PackageManager manager = getPackageManager();
            Intent intent =manager.getLaunchIntentForPackage("com.android.stk");
            if (intent != null)
                startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }
}
