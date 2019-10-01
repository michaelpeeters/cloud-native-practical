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

    public List<Cocktail> getAll() {
        return cocktailRepository.getAll();
    }

    public Cocktail get(String cocktailId) {
        Cocktail cocktail = cocktailRepository.get(cocktailId);
        if (cocktail == null) {
            throw new CocktailNotFoundException("Cocktail with id " + cocktailId + " is not found.");
        }
        return cocktail;
    }
}

