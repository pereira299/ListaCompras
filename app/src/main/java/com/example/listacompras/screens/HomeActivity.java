package com.example.listacompras.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.example.listacompras.database.AppDatabase;
import com.example.listacompras.R;
import com.example.listacompras.adapters.ListAdapter;
import com.example.listacompras.dao.ShoppingListDao;
import com.example.listacompras.database.DatabaseProvider;
import com.example.listacompras.entities.ListaWithItems;
import com.example.listacompras.entities.ShoppingListEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    AppDatabase banco;
    ImageButton newListBtn;
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;
    ListView lists;
    ListAdapter listAdapter;
    ArrayList<ListaWithItems> shoppingList;
    ShoppingListsObserver listsObserver;
    ShoppingListDao listDao;
    private ListaWithItems selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newListBtn = (ImageButton) findViewById(R.id.newProductBtn);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        lists = (ListView) findViewById(R.id.list_item);

        listsObserver = new ShoppingListsObserver();
        banco = DatabaseProvider.getDatabase(getApplicationContext());
        listDao = banco.shoppingListDao();
        listDao.getAllWithItems().observe(this, listsObserver);

        shoppingList = new ArrayList<>();
        listAdapter = new ListAdapter(this, shoppingList);
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

        newListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAdd(v);
            }
        });

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedList = shoppingList.get(position);
                goToDetails();
            }
        });
    }

    private void goToDetails(){
        Intent i = new Intent(this, DetailsListActivity.class);
        i.putExtra("selectedList", selectedList);
        startActivity(i);
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
        Intent intent = new Intent(this, NewListActivity.class);
        startActivity(intent);
    }



    class ShoppingListsObserver implements Observer<List<ListaWithItems>> {

        @Override
        public void onChanged(List<ListaWithItems> shoppingListEntities) {
            shoppingList.clear();
            shoppingList.addAll(shoppingListEntities);
            Collections.sort(shoppingList, new Comparator<ListaWithItems>() {
                @Override
                public int compare(ListaWithItems u1, ListaWithItems u2) {
                    if (u1.getList().isPriority() && !u2.getList().isPriority()) {
                        return -1;
                    } else if (!u1.getList().isPriority() && u2.getList().isPriority()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            listAdapter.notifyDataSetChanged();
        }
    }
}