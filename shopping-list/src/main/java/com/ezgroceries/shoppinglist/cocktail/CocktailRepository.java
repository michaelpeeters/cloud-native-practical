package com.ezgroceries.shoppinglist.cocktail;

import java.util.List;

public interface CocktailRepository {

    Cocktail get(String cocktailId);

    List<Cocktail> search(String cocktailName);
}
