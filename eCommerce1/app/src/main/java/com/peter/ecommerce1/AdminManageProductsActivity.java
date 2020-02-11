package com.peter.ecommerce1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminManageProductsActivity extends AppCompatActivity
{
    private ImageView changeImage;
    private EditText pName, pPrice,pDescription;
    private Button applyChanges;

    private DatabaseReference productsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_products);

        productsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        initViews();
    }

    private void initViews()
    {
        changeImage = (ImageView) findViewById(R.id.manage_product_image);
        pName = (EditText) findViewById(R.id.manage_product_name);
        pPrice = (EditText) findViewById(R.id.manage_product_price);
        pDescription = (EditText) findViewById(R.id.manage_product_description);
        applyChanges = (Button) findViewById(R.id.manage_changes_btn);
    }
}
