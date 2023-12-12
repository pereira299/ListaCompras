package com.example.listacompras.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.listacompras.R;
import com.example.listacompras.adapters.ListAdapter;
import com.example.listacompras.adapters.ProductsAdapter;
import com.example.listacompras.dao.ProductDao;
import com.example.listacompras.database.AppDatabase;
import com.example.listacompras.database.DatabaseProvider;
import com.example.listacompras.entities.ListaWithItems;
import com.example.listacompras.entities.ProductEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    AppDatabase banco;
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;
    ImageButton addBtn;
    ListView lists;
    ProductsAdapter listAdapter;
    ArrayList<ProductEntity> productsList;
    ProductListObserver productObserver;
    ProductDao productDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        addBtn = (ImageButton) findViewById(R.id.newProductBtn);
        lists = (ListView) findViewById(R.id.product_list);

        productObserver = new ProductsActivity.ProductListObserver();
        banco = DatabaseProvider.getDatabase(getApplicationContext());
        productDao = banco.productDao();
        productDao.getAll().observe(this, productObserver);
        productsList = new ArrayList<>();
        listAdapter = new ProductsAdapter(this, productsList);
        lists.setAdapter(listAdapter);

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
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAdd(v);
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

    private void goToAdd(View v) {
        Intent intent = new Intent(this, NewProductActivity.class);
        startActivity(intent);
    }

    class ProductListObserver implements Observer<List<ProductEntity>> {
        @Override
        public void onChanged(List<ProductEntity> productEntities) {
            productsList.clear();
            productsList.addAll(productEntities);
            listAdapter.notifyDataSetChanged();
        }
    }
}
