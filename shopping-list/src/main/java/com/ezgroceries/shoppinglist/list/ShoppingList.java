package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktail.Cocktail;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShoppingList {

    @JsonView(View.Summary.class)
    private final UUID shoppingListId;
    @JsonView(View.Summary.class)
    private String name;
    private List<Cocktail> cocktails;

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
