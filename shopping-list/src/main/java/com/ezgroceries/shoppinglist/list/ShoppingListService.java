package com.ezgroceries.shoppinglist.list;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    List<ShoppingList> getAll() {
        return shoppingListRepository.getAll();
    }

    ShoppingList add(ShoppingList shoppingList) {
        return shoppingListRepository.add(shoppingList);
    }
}

