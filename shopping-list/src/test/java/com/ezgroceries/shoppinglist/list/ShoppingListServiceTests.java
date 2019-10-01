package com.ezgroceries.shoppinglist.list;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
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

public class ShoppingListServiceTests {
	@Autowired
	private ShoppingListService shoppingListService;
	@Before
	public void setup(){

	}

	@Test
	public void getAll() {
		assertThat(shoppingListService.getAll(), is(not(nullValue())));
	}

	@Test
	public void add() {
		ShoppingList shoppingList = new ShoppingList("haha");
		assertThat(shoppingListService.add(shoppingList), is(shoppingList));
	}

}
