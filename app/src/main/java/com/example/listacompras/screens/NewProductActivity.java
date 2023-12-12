package com.example.listacompras.screens;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.listacompras.R;
import com.example.listacompras.adapters.SetorAdapter;
import com.example.listacompras.dao.ProductDao;
import com.example.listacompras.dao.SetorDao;
import com.example.listacompras.database.AppDatabase;
import com.example.listacompras.database.DatabaseProvider;
import com.example.listacompras.entities.ListaWithItems;
import com.example.listacompras.entities.ProductEntity;
import com.example.listacompras.entities.SetorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NewProductActivity extends AppCompatActivity {
    AppDatabase banco;
    SetorDao setorDao;
    ImageButton setorTab;
    ImageButton homeTab;
    ImageButton productsTab;
    TextView productNameTxt;
    TextView productPriceTxt;
    Spinner productUnitySpn;
    Spinner productSetorSpn;
    Button saveBtn;

    String[] setorNames = new String[0];
    SetorListsObserver setorObserver;
    ArrayAdapter<String> setorAdapter;
    ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        setorTab = (ImageButton) findViewById(R.id.setorTab);
        homeTab = (ImageButton) findViewById(R.id.homeTab);
        productsTab = (ImageButton) findViewById(R.id.productsTab);
        productNameTxt = (TextView) findViewById(R.id.productNameTxt);
        productPriceTxt = (TextView) findViewById(R.id.productPriceTxt);
        productSetorSpn = (Spinner) findViewById(R.id.productSetorSpn);
        productUnitySpn = (Spinner) findViewById(R.id.productUnitySpn);
        saveBtn = (Button) findViewById(R.id.productSaveBtn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productUnitySpn.setAdapter(adapter);

        productUnitySpn.setAdapter(adapter);

        banco = DatabaseProvider.getDatabase(getApplicationContext());
        setorDao = banco.setorDao();
        productDao = banco.productDao();
        setorObserver = new SetorListsObserver();
        setorDao.getAll().observe(this, setorObserver);
        setorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, setorNames);
        setorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSetorSpn.setAdapter(setorAdapter);
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
        String name = productNameTxt.getText().toString();
        double price = Double.parseDouble(productPriceTxt.getText().toString());
        String unity = productUnitySpn.getSelectedItem().toString();
        //String setor = productSetorSpn.getSelectedItem().toString();

        if(name.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Nome invalido")
                    .setMessage("Você deve informar o nome da lista")
                    .show();
            return;
        } else if (price <= 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Preço invalido")
                    .setMessage("Você deve informar o preço do produto")
                    .show();
            return;
        } else if (unity.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Unidade invalida")
                    .setMessage("Você deve informar a unidade de medida do produto")
                    .show();
            return;
        }

        switch (unity){
            case "Unidade":
                unity = "Und";
                break;
            case "Quilograma":
                unity = "Kg";
                break;
            case "Grama":
                unity = "g";
                break;
            case "Miligrama":
                unity = "mg";
                break;
            case "Litro":
                unity = "L";
                break;
            case "Mililitro":
                unity = "mL";
                break;
            case "Metro":
                unity = "m";
                break;
            case "Centimetro":
                unity = "cm";
                break;
            case "Milimetro":
                unity = "mm";
                break;
        }

        ProductEntity product = new ProductEntity();
        product.setName(name);
        product.setPrice(price);
        product.setUnity(unity);
        productDao.insert(product);
        finish();
    }

    class SetorListsObserver implements Observer<List<SetorEntity>> {
        @Override
        public void onChanged(@Nullable final List<SetorEntity> setores) {
            if (setores != null) {
                setorNames = new String[setores.size()];
                for (int i = 0; i < setores.size(); i++) {
                    setorNames[i] = setores.get(i).getName();
                }
                setorAdapter.notifyDataSetChanged();
                // Do something with setorNames
            }
        }
    }
}
