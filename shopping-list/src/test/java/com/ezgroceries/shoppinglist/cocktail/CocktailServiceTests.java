package com.ezgroceries.shoppinglist.cocktail;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

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
	public void setup(){

	}

	@Test
	public void getAll() {
		assertThat(cocktailService.getAll(), is(notNullValue()));
	}

}
