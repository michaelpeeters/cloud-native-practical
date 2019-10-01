package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.cocktail.CocktailController;
import com.ezgroceries.shoppinglist.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.cocktail.CocktailService;
import com.ezgroceries.shoppinglist.cocktail.DummyCocktailRepository;
import com.ezgroceries.shoppinglist.list.DummyShoppingListRepository;
import com.ezgroceries.shoppinglist.list.ShoppingListController;
import com.ezgroceries.shoppinglist.list.ShoppingListService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({ShoppingListController.class, ShoppingListService.class, DummyShoppingListRepository.class, CocktailController.class, CocktailService.class,
        DummyCocktailRepository.class}) // A @Component injected with ExampleService
public class MyTestConfiguration {

    @Bean
    public CocktailRepository cocktailRepository() {
        return new DummyCocktailRepository();
    }
}