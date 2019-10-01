package com.ezgroceries.shoppinglist.integration;

import com.ezgroceries.shoppinglist.cocktail.CocktailController;
import com.ezgroceries.shoppinglist.cocktail.CocktailDBRepository;
import com.ezgroceries.shoppinglist.cocktail.CocktailRepository;
import com.ezgroceries.shoppinglist.cocktail.CocktailService;
import com.ezgroceries.shoppinglist.list.DummyShoppingListRepository;
import com.ezgroceries.shoppinglist.list.ShoppingListController;
import com.ezgroceries.shoppinglist.list.ShoppingListService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({ShoppingListController.class, ShoppingListService.class, DummyShoppingListRepository.class, CocktailController.class, CocktailService.class,
        CocktailDBRepository.class}) // A @Component injected with ExampleService
public class MyIntegrationTestConfiguration {

    @Bean
    public CocktailRepository cocktailRepository() {
        return new CocktailDBRepository();
    }
}