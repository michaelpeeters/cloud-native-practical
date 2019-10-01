package com.ezgroceries.shoppinglist.cocktail;

import io.micrometer.core.instrument.util.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CocktailDBRepository implements CocktailRepository {

    private final List<Cocktail> cocktails = new CopyOnWriteArrayList<>();

    @Autowired
    private CocktailDBClient cocktailDBClient;

    public CocktailDBRepository() {
        cocktails.addAll(Arrays.asList(new Cocktail(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita", "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the " + "salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                new Cocktail(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita", "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt"))));
    }


    @Override
    public Cocktail get(String cocktailId) {
        return cocktails.stream().filter(cocktail -> cocktail.getCocktailId().toString().equals(cocktailId)).findFirst().orElse(null);
    }

    @Override
    public List<Cocktail> search(String cocktailName) {
        if (StringUtils.isEmpty(cocktailName ))
            return cocktails;
        // else search remotely
        CocktailDBResponse cocktailDBResponse = cocktailDBClient.searchCocktails(cocktailName);
        List<Cocktail> cocktails = cocktailDBResponse.getDrinks().stream()
                .map(dbResponse -> new Cocktail(UUID.randomUUID(), dbResponse.getStrDrink(), dbResponse.getStrGlass(),
                        dbResponse.getStrInstructions(), dbResponse.getStrDrinkThumb(), dbResponse.convertToIngredientList()))
                .collect(Collectors.toList());
        this.cocktails.addAll(cocktails);
        return cocktails;
    }

}
