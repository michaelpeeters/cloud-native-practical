package com.ezgroceries.shoppinglist.list;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class DummyShoppingListRepository implements ShoppingListRepository {

    private List<ShoppingList> shoppingLists = new CopyOnWriteArrayList<>();

    @Override
    public List<ShoppingList> getAll() {
        return shoppingLists;
    }
    @Override
    public ShoppingList add(ShoppingList shoppingList) {
        shoppingLists.add(shoppingList);
        return shoppingList;
    }
    @Override
    public ShoppingList get(String id) {
        return shoppingLists.stream().filter(shoppingList -> shoppingList.getShoppingListId().toString().equals(id)).findFirst()
                .orElse(null);
    }
}
