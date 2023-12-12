package com.example.listacompras.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listacompras.entities.ProductEntity;
import com.example.listacompras.entities.SetorEntity;
import com.example.listacompras.entities.SetorWithProducts;

import java.util.List;
import java.util.Map;

@Dao
public interface SetorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SetorEntity setor);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(SetorEntity setor);

    @Delete
    void delete(SetorEntity setor);

    @Query("SELECT * FROM setor")
    LiveData<List<SetorEntity>> getAll();

    @Query("SELECT * FROM setor WHERE id = :id")
    LiveData<SetorEntity> getOne(long id);

    @Query("SELECT * FROM setor")
    LiveData<List<SetorWithProducts>> getAllWithProducts();

    @Query("SELECT * FROM setor WHERE id = :id")
    LiveData<SetorWithProducts> getOneWithProducts(long id);
}
