package com.ezgroceries.shoppinglist.cocktail;

import java.util.List;

public interface CocktailRepository {

    List<Cocktail> getAll();

    Cocktail get(String cocktailId);
}
