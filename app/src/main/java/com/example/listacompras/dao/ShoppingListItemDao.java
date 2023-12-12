package com.example.listacompras.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listacompras.entities.ShoppingListItemEntity;

import java.util.List;

@Dao
public interface ShoppingListItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ShoppingListItemEntity shoppingListItem);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ShoppingListItemEntity shoppingListItem);

    @Delete
    void delete(ShoppingListItemEntity shoppingListItem);

    @Query("SELECT * FROM shopping_list_item")
    LiveData<List<ShoppingListItemEntity>> getAll();

    @Query("SELECT * FROM shopping_list_item WHERE id = :id")
    LiveData<ShoppingListItemEntity> getOne(long id);
}
