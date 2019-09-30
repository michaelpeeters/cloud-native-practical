package com.ezgroceries.shoppinglist.cocktail;

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
    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailController(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @GetMapping(value = "/cocktails")
    public @ResponseBody
    List<Cocktail> get(@RequestParam(required = false) String search) {
        logger.info("search criteria: " + search);
        return cocktailRepository.getAll();
    }


}