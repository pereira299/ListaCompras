package com.example.listacompras.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class ListaWithItems implements Serializable {
    @Embedded public ShoppingListEntity list;
    @Relation(
            parentColumn = "id",
            entityColumn = "list_id"
    )
    public List<ShoppingListItemEntity> items;

    public ShoppingListEntity getList() {
        return list;
    }

    public void setList(ShoppingListEntity list) {
        this.list = list;
    }

    public List<ShoppingListItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItemEntity> items) {
        this.items = items;
    }
}
