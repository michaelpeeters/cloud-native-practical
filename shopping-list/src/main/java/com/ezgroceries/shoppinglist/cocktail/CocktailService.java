package com.ezgroceries.shoppinglist.cocktail;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    List<Cocktail> getAll() {
        return cocktailRepository.getAll();
    }
}

