package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktail.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.CocktailService;
import com.ezgroceries.shoppinglist.cocktail.CocktailView;
import com.fasterxml.jackson.annotation.JsonView;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class ShoppingListController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ShoppingListService shoppingListService;
    private final CocktailService cocktailService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService, CocktailService cocktailService) {
        this.shoppingListService = shoppingListService;
        this.cocktailService = cocktailService;
    }


    @JsonView(ShoppingListView.Summary.class)
    @PostMapping(value = "/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public @ResponseBody
    ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.add(shoppingList);
    }

    @JsonView(CocktailView.Id.class)
    @PostMapping(value = "/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    List<Cocktail> createShoppingList(@PathVariable String shoppingListId, @RequestBody List<Cocktail> cocktailIdentifiedByIds) {
        final ShoppingList shoppingList = shoppingListService.get(shoppingListId);
        if (shoppingList == null) {
            throw new ShoppingListNotFoundException("Shoppinglist with id " + shoppingListId + " not found");
        }
        // TODO move to service?
        List<Cocktail> cocktails = cocktailIdentifiedByIds.stream().map(cocktail -> cocktailService.get(cocktail.getCocktailId().toString()))
                .collect(Collectors.toList());
        shoppingList.setCocktails(cocktails);
        return cocktails;
    }

    private ResponseEntity<Void> entityWithLocation(Object resourceId) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{itemId}").buildAndExpand(resourceId).toUri();
        return ResponseEntity.created(location).build(); // Return something other than null
    }
}