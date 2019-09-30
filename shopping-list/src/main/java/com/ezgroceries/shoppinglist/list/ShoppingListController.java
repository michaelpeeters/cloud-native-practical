package com.ezgroceries.shoppinglist.list;

import com.fasterxml.jackson.annotation.JsonView;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class ShoppingListController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @JsonView(View.Summary.class)
    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public @ResponseBody
    ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.add(shoppingList);
    }

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{itemId}").buildAndExpand(resourceId).toUri();
        return ResponseEntity.created(location).build(); // Return something other than null
    }
}