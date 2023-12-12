package com.example.listacompras.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.listacompras.dao.ProductDao;
import com.example.listacompras.dao.SetorDao;
import com.example.listacompras.dao.ShoppingListDao;
import com.example.listacompras.dao.ShoppingListItemDao;
import com.example.listacompras.entities.ProductEntity;
import com.example.listacompras.entities.SetorEntity;
import com.example.listacompras.entities.ShoppingListEntity;
import com.example.listacompras.entities.ShoppingListItemEntity;

@Database(entities = {ShoppingListEntity.class, ShoppingListItemEntity.class, ProductEntity.class, SetorEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShoppingListDao shoppingListDao();

    public abstract ProductDao productDao();

    public abstract SetorDao setorDao();

    public abstract ShoppingListItemDao shoppingListItemDao();
}