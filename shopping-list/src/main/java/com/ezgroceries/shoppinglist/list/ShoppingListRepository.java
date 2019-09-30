package com.ezgroceries.shoppinglist.list;

import java.util.List;

public interface ShoppingListRepository {

    List<ShoppingList> getAll();

    ShoppingList add(ShoppingList shoppingList);

}
