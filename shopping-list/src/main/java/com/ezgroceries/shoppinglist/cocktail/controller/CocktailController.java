package com.ezgroceries.shoppinglist.cocktail.controller;

import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CocktailController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping(value = "/cocktails")
    public @ResponseBody
    List<Cocktail> get(@RequestParam(required = false) String search) {
        logger.info("search criteria: " + search);
        return cocktailService.search(search);
    }


}