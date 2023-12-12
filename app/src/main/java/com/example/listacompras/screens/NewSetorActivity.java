package com.example.listacompras.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.listacompras.R;
import com.example.listacompras.dao.ProductDao;
import com.example.listacompras.database.AppDatabase;
import com.example.listacompras.database.DatabaseProvider;
import com.example.listacompras.entities.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewSetorActivity extends AppCompatActivity {
    AppDatabase banco;
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;
    EditText setorName;
    ListView list;
    ProductListObserver productObserver;
    ProductDao productDao;
    ArrayList<String> productsList = new ArrayList<>();
    ArrayList<ProductEntity> allProducts = new ArrayList<>();
    ArrayAdapter listAdapter;
    ArrayList<ProductEntity> selectedProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_setor);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        EditText setorName = (EditText) findViewById(R.id.setor_name_txt);
        list = (ListView) findViewById(R.id.new_setor_list);
        banco = DatabaseProvider.getDatabase(getApplicationContext());
        productDao = banco.productDao();
        productObserver = new ProductListObserver();
        productDao.getAll().observe(this, productObserver);
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, productsList);
        list.setAdapter(listAdapter);
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //check if item is not selected
                //add item to selected items

                CheckedTextView checkedTextView = (CheckedTextView) view;
                if(!checkedTextView.isChecked()){
                    selectedProducts.add(allProducts.get(position));
                    //check item
                    checkedTextView.setChecked(true);
                }else{
                    selectedProducts.remove(allProducts.get(position));
                    checkedTextView.setChecked(false);
                }

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

    private void save(View v) {
        //save selected items to database
        //go to setores activity

    }
    class ProductListObserver implements Observer<List<ProductEntity>> {
        @Override
        public void onChanged(List<ProductEntity> productEntities) {
            productsList.clear();
            allProducts.clear();
            allProducts.addAll(productEntities);
            productsList.addAll(productEntities.stream().map(ProductEntity::getName).collect(Collectors.toList()));
            listAdapter.notifyDataSetChanged();
        }
    }
}
