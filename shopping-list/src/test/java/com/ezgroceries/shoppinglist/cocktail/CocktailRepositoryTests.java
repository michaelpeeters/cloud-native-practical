package com.ezgroceries.shoppinglist.cocktail;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.cocktail.persistence.CocktailRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CocktailRepositoryTests {

    @Autowired
    private CocktailRepository cocktailRepository;

    @Before
    public void setup() {

    }


    @Test
    public void get() {
        assertThat(cocktailRepository.findByName("abc"), is(empty()));
    }

    @Test
    public void search() {
        assertThat(cocktailRepository.findByName("abc"), is(empty()));
    }


}
