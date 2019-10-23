package com.ezgroceries.shoppinglist.cocktail.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {

    List<CocktailEntity> findByName(String cocktailName);

}
