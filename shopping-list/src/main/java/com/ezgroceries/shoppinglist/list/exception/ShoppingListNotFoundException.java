package com.ezgroceries.shoppinglist.list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such ShoppingList")  // 404
public class ShoppingListNotFoundException extends RuntimeException {

    public ShoppingListNotFoundException(String message) {
        super(message);
    }

    public ShoppingListNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}