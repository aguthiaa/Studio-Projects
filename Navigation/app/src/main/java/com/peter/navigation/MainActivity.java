package com.peter.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private NavigationView mNav;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView userImage;
    private TextView userName, userStatus;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");


        mNav = (NavigationView) findViewById(R.id.nav_menu);

        View navView = mNav.inflateHeaderView(R.layout.navigation_header_layout);

        userImage = (ImageView) navView.findViewById(R.id.user_profile_image);
        userName = (TextView) navView.findViewById(R.id.user_user_name);
        userStatus = (TextView) navView.findViewById(R.id.user_status);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                openMenu(menuItem);
                return false;
            }
        });

    }

    private void openMenu(MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.home_page:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;


            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;


            case R.id.friends:
                Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show();
                break;


            case R.id.messages:
                Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
                break;


            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;


            case R.id.logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
