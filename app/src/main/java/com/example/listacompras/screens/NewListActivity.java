package com.example.listacompras.screens;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listacompras.R;
import com.example.listacompras.dao.ShoppingListDao;
import com.example.listacompras.database.AppDatabase;
import com.example.listacompras.database.DatabaseProvider;
import com.example.listacompras.entities.ShoppingListEntity;

public class NewListActivity extends AppCompatActivity {
    AppDatabase banco;
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;
    TextView nameTv;
    Switch prioritySw;
    Button saveBtn;
    ShoppingListDao listDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        nameTv = (TextView) findViewById(R.id.nameTv);
        prioritySw = (Switch) findViewById(R.id.prioritySw);
        saveBtn = (Button) findViewById(R.id.saveListBtn);
        setorTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSetor(v);
            }
        });

        banco = DatabaseProvider.getDatabase(getApplicationContext());
        listDao = banco.shoppingListDao();
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
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
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
        String name = nameTv.getText().toString();
        Boolean priority = prioritySw.isChecked();

        if(name.isEmpty()){
            new AlertDialog.Builder(this)
                    .setTitle("Nome invalido")
                    .setMessage("Você deve informar o nome da lista")
                    .show();
            return;
        }

        ShoppingListEntity  list = new ShoppingListEntity();
        list.setName(name);
        list.setPriority(priority);
        list.setTotalPrice(0);
        listDao.insert(list);
        finish();
    }
}
