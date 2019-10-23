package com.ezgroceries.shoppinglist.cocktail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Cocktail")  // 404
public class CocktailNotFoundException extends RuntimeException {

    public CocktailNotFoundException(String message) {
        super(message);
    }

    public CocktailNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}