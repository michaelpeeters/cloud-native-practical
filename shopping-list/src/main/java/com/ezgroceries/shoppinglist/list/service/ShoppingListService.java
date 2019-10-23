package com.ezgroceries.shoppinglist.list.service;

import com.ezgroceries.shoppinglist.list.model.ShoppingList;
import com.ezgroceries.shoppinglist.list.persistence.ShoppingListEntity;
import com.ezgroceries.shoppinglist.list.exception.ShoppingListNotFoundException;
import com.ezgroceries.shoppinglist.list.persistence.ShoppingListRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public List<ShoppingList> getAll() {
        return StreamSupport.stream(shoppingListRepository.findAll().spliterator(), false)
                .map(shoppingListEntity -> new ShoppingList(shoppingListEntity)).collect(Collectors.toList());
    }

    public ShoppingList add(ShoppingList shoppingList) {
        shoppingListRepository.save(new ShoppingListEntity(shoppingList.getName()));
        return shoppingList;
    }

    public ShoppingList get(String id) {
        try {
            Optional<ShoppingListEntity> result = shoppingListRepository.findById(UUID.fromString(id));
            if (!result.isPresent()) {
                throw new ShoppingListNotFoundException("Shopping list not found with id " + id);
            }
            return new ShoppingList(result.get());
        } catch (IllegalArgumentException e) {
            throw new ShoppingListNotFoundException("Shopping list id " + id + " is not a UUID - " + e.getMessage(), e);
        }
    }
}

