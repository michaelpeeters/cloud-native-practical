package com.ezgroceries.shoppinglist.cocktail;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.MyTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MyTestConfiguration.class)
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

    @Test
    public void getId() {
        assertThat(cocktailService.get("23b3d85a-3928-41c0-a533-6538a71e17c4"), is(notNullValue()));
    }

    @Test(expected = CocktailNotFoundException.class)
    public void getNotFound() {
        cocktailService.get("nonexistingid");
    }

}
