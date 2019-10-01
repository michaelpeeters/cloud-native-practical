package com.ezgroceries.shoppinglist.cocktail;

import static org.hamcrest.Matchers.empty;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(MyTestConfiguration.class)
public class CocktailRepositoryTests {

    @Autowired
    private CocktailRepository cocktailRepository;

    @Before
    public void setup() {

    }


    @Test
    public void get() {
        assertThat(cocktailRepository.get("abc"), is(nullValue()));
    }

    @Test
    public void search() {
        assertThat(cocktailRepository.search("abc"), is(not(empty())));
    }
	@Configuration
	@Import({DummyCocktailRepository.class}) // A @Component injected with ExampleService
	static class Config {

	}


}
