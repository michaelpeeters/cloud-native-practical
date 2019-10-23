package com.ezgroceries.shoppinglist.cocktail.service;

import com.ezgroceries.shoppinglist.cocktail.exception.CocktailNotFoundException;
import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }


    public Cocktail get(String cocktailId) {

        try {
            Optional<CocktailEntity> result = cocktailRepository.findById(UUID.fromString(cocktailId));
            if (!result.isPresent()) {
                throw new CocktailNotFoundException("Cocktail with id " + cocktailId + " is not found.");
            }

            return new Cocktail(result.get());
        } catch (IllegalArgumentException e) {
            throw new CocktailNotFoundException("ID " + cocktailId + " is not a UUID - " + e.getMessage(), e);

        }

    }

    public List<Cocktail> search(String cocktailName) {
        return cocktailRepository.findByName(cocktailName).stream().map(cocktailEntity -> new Cocktail(cocktailEntity)).collect(Collectors.toList());
    }

//    public List<CocktailDBResponse> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        // TODO
//        //Get all the idDrink attributes
//        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());
//
//        //Get all the ones we already have from our DB, use a Map for convenient lookup
//        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findById(ids).stream().collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));
//
//        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
//        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
//            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
//            if (cocktailEntity == null) {
//                CocktailEntity newCocktailEntity = new CocktailEntity();
//                newCocktailEntity.setUuid(UUID.randomUUID());
//                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
//                newCocktailEntity.setName(drinkResource.getStrDrink());
//                cocktailEntity = cocktailRepository.save(newCocktailEntity);
//            }
//            return cocktailEntity;
//        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
//        return mergeAndTransform(drinks, allEntityMap);
//    }

//    private List<CocktailDBResponse> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
//         return drinks.stream().map(drinkResource -> new CocktailDBResponse(allEntityMap.get(drinkResource.getIdDrink()).getId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
//                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getIngredients(drinkResource))).collect(Collectors.toList());
//    }
}

