package com.peter.ecommerce1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SellersHomeActivity extends AppCompatActivity
{
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers_home);

        toolbar = (Toolbar) findViewById(R.id.seller_home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.seller_bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {

                switch (menuItem.getItemId())
                {
                    case R.id.navigation_home2:

                        toolbar.setTitle("Home");
                        return true;



                    case R.id.navigation_dashboard:
                        toolbar.setTitle("Add");
                        Intent addIntent = new Intent(SellersHomeActivity.this, SellerProductsCategoryActivity.class);
                        startActivity(addIntent);
                        return true;



                    case R.id.navigation_notifications:
                        toolbar.setTitle("Logout");
                        final FirebaseAuth mAuth;
                        mAuth = FirebaseAuth.getInstance();
                        mAuth.signOut();
                        Intent logoutIntent = new Intent(SellersHomeActivity.this, MainActivity.class);
                        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(logoutIntent);
                        finish();

                        return true;
                }
                return false;
            }
        });

    }
}
