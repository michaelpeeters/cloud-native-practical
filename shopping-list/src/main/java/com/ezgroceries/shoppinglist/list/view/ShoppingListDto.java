package com.ezgroceries.shoppinglist.list.view;

import com.ezgroceries.shoppinglist.list.model.ShoppingList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * DTO object for custom responses of our Json API. Exploring DTO as an alternative to @JsonType approach.
 */
public class ShoppingListDto {

    private final UUID shoppingListId;
    private final String name;
    private final List<String> ingredients;

    public ShoppingListDto(final ShoppingList shoppingList) {
        this.shoppingListId = shoppingList.getShoppingListId();
        this.name = shoppingList.getName();
        this.ingredients = shoppingList.getCocktails().stream().flatMap(cocktail -> cocktail.getIngredients().stream()).collect(Collectors.toList());
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
