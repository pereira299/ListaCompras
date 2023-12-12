package com.example.listacompras.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listacompras.entities.ListaWithItems;
import com.example.listacompras.entities.SetorEntity;
import com.example.listacompras.entities.ShoppingListEntity;

import java.util.List;

@Dao
public interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ShoppingListEntity shoppingList);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ShoppingListEntity shoppingList);

    @Delete
    void delete(ShoppingListEntity shoppingList);

    @Query("SELECT * FROM shopping_lists")
    LiveData<List<ShoppingListEntity>> getAll();

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    LiveData<ShoppingListEntity> getOne(long id);

    @Query("SELECT * FROM shopping_lists")
    LiveData<List<ListaWithItems>> getAllWithItems();

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    LiveData<ListaWithItems> getOneWithItems(long id);
}