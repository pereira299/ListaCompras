package com.example.listacompras.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.listacompras.entities.ProductEntity;
import com.example.listacompras.entities.ProductsWithItems;
import com.example.listacompras.entities.ShoppingListItemEntity;

import java.util.List;
import java.util.Map;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ProductEntity product);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ProductEntity product);

    @Delete
    void delete(ProductEntity product);

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> getAll();

    @Query("SELECT * FROM products WHERE id = :id")
    LiveData<ProductEntity> getOne(long id);

    @Transaction
    @Query("SELECT * FROM products")
    LiveData<List<ProductsWithItems>> getAllWithItems();

    @Transaction
    @Query("SELECT * FROM products WHERE id = :id")
    LiveData<ProductsWithItems> getOneWithItem(long id);
}
