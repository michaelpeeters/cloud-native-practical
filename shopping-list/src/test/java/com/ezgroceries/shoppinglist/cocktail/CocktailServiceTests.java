package com.ezgroceries.shoppinglist.cocktail;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.cocktail.exception.CocktailNotFoundException;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CocktailServiceTests {

    @Autowired
    private CocktailService cocktailService;

    @Before
    public void setup() {

    }

    @Test
    public void search() {
        assertThat(cocktailService.search("ok"), is(notNullValue()));
    }

    @Test(expected = CocktailNotFoundException.class)
    public void getId() {
        assertThat(cocktailService.get("23b3d85a-3928-41c0-a533-6538a71e17c4"), is(nullValue()));
    }

    @Test(expected = CocktailNotFoundException.class)
    public void getNotFound() {
        cocktailService.get("nonexistingid");
    }

}
