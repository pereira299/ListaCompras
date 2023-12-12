package com.example.listacompras.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listacompras.R;

public class AddProductSetorActivity extends AppCompatActivity {
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_setor);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        setorTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSetor(v);
            }
        });

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLists(v);
            }
        });

        productsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProducts(v);
            }
        });
    }

    private void goToSetor(View v) {
        Intent intent = new Intent(this, SetoresActivity.class);
        startActivity(intent);
    }

    private void goToLists(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void goToProducts(View v) {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }
}
