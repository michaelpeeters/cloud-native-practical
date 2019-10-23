package com.ezgroceries.shoppinglist.list;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.list.model.ShoppingList;
import com.ezgroceries.shoppinglist.list.view.ShoppingListDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class ShoppingListDtoTest {

    private ShoppingList shoppingList;
    private ShoppingListDto shoppingListDto;

    @Before
    public void setup() {
        shoppingList = new ShoppingList("test");
        shoppingListDto = new ShoppingListDto(shoppingList);

    }

    @Test
    public void getName() {
        assertThat(shoppingList.getName(), is("test"));
    }


    @Test
    public void getId() {
        assertThat(shoppingList.getShoppingListId(), is(notNullValue()));
    }


    @Test
    public void getIngredients() {
        List<String> ingredients = shoppingListDto.getIngredients();

        // crosschecking our functional approach with the imperative one...
        List<String> ingredientsImperative = new ArrayList();
        for (Cocktail cocktail : shoppingList.getCocktails()) {
            ingredientsImperative.addAll(cocktail.getIngredients());
        }

        assertThat(ingredients, is(ingredientsImperative));
    }
}
