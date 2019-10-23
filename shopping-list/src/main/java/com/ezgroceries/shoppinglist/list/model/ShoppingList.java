package com.ezgroceries.shoppinglist.list.model;

import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.list.persistence.ShoppingListEntity;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShoppingList {

    private final UUID shoppingListId;
    private String name;
    private List<Cocktail> cocktails;

    public ShoppingList(ShoppingListEntity shoppingListEntity) {
        this(shoppingListEntity.getUuid(), shoppingListEntity.getName(), null);
    }

    public ShoppingList(String name) {
        this(null, name, null);
    }

    ShoppingList(UUID shoppingListId, String name, List<Cocktail> cocktails) {
        this.shoppingListId = shoppingListId == null ? UUID.randomUUID() : shoppingListId;
        this.name = name;
        this.cocktails = cocktails == null ? new CopyOnWriteArrayList<>() : cocktails;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
