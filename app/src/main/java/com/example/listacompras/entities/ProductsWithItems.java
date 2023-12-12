package com.example.listacompras.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class ProductsWithItems implements Serializable {
    @Embedded
    public ProductEntity product;
    @Relation(
            parentColumn = "id",
            entityColumn = "product_id"
    )
    public List<ShoppingListItemEntity> items;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public List<ShoppingListItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ShoppingListItemEntity> items) {
        this.items = items;
    }
}
