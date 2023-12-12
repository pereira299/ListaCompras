package com.example.listacompras.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class SetorWithProducts implements Serializable {

    @Embedded
    public SetorEntity setor;
    @Relation(
            parentColumn = "id",
            entityColumn = "setor_id"
    )
    public List<ProductEntity> products;


    public SetorEntity getSetor() {
        return setor;
    }

    public void setSetor(SetorEntity setor) {
        this.setor = setor;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
