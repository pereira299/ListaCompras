package com.example.listacompras.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.listacompras.R;
import com.example.listacompras.dao.SetorDao;
import com.example.listacompras.database.AppDatabase;
import com.example.listacompras.database.DatabaseProvider;
import com.example.listacompras.entities.ListaWithItems;
import com.example.listacompras.entities.SetorEntity;

import java.util.List;

public class SetoresActivity extends AppCompatActivity {
    AppDatabase banco;
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;
    ImageButton addBtn;
    ListView list;

    private SetorDao setorDao;
    ArrayAdapter<String> setorAdapter;
    List<String> setorNames = new java.util.ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setores);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        addBtn = (ImageButton) findViewById(R.id.newSetorBtn);
        list = (ListView) findViewById(R.id.setor_list);
        banco = DatabaseProvider.getDatabase(getApplicationContext());
        setorDao = banco.setorDao();
        setorDao.getAll().observe(this, new SetorListObserver());
        setorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, setorNames);
        list.setAdapter(setorAdapter);
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
        Intent intent = new Intent(this, NewSetorActivity.class);
        startActivity(intent);
    }

    class SetorListObserver implements Observer<List<SetorEntity>> {
        @Override
        public void onChanged(List<SetorEntity> setorEntities) {
            setorNames.clear();
            for (SetorEntity setor : setorEntities) {
                setorNames.add(setor.getName());
            }
            setorAdapter.notifyDataSetChanged();
        }
    }
}
