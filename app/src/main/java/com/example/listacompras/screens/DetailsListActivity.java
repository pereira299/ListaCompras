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
import com.example.listacompras.adapters.ListDetailsAdapter;
import com.example.listacompras.entities.ListaWithItems;
import com.example.listacompras.entities.ShoppingListItemEntity;

import java.util.ArrayList;
import java.util.List;

public class DetailsListActivity extends AppCompatActivity {
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;
    ListView listItem;
    List<ShoppingListItemEntity> shoppingListItems;
    ListDetailsAdapter listDetailsAdapter;
    private ListaWithItems selectedList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        listItem = (ListView) findViewById(R.id.details_list_item);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            selectedList = (ListaWithItems) extras.getSerializable("selectedList");
        }

        shoppingListItems = selectedList.items;
        listDetailsAdapter = new ListDetailsAdapter(this, shoppingListItems);
        listItem.setAdapter(listDetailsAdapter);
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

    protected void goToSetor(View v) {
        Intent intent = new Intent(this, SetoresActivity.class);
        startActivity(intent);
    }

    protected void goToLists(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    protected void goToProducts(View v) {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }

    class ShoppingListItemObserver implements Observer<List<ShoppingListItemEntity>> {

        @Override
        public void onChanged(List<ShoppingListItemEntity> shoppingListItemEntities) {
            shoppingListItems.clear();
            shoppingListItems.addAll(shoppingListItemEntities);
            listDetailsAdapter.notifyDataSetChanged();
        }
    }
}
