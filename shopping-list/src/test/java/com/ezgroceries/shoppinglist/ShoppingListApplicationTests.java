package com.ezgroceries.shoppinglist;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.list.ShoppingListController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Import(MyTestConfiguration.class)
public class ShoppingListApplicationTests {

    @Autowired
    private ShoppingListController shoppingListController;

    private HttpHeaders httpHeaders;

    private String baseUrl;

    @Before
    public void setup() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
       // baseUrl = "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
        assertThat(shoppingListController, is(notNullValue()));
    }

    @Test
    public void createNewShoppingList() throws Exception {
        HttpEntity<String> request = new HttpEntity<String>("{\"name\": \"Stephanie's birthday\"}", httpHeaders);

//        ResponseEntity result = restTemplate.postForEntity(baseUrl + "/shopping-lists", request, String.class);
//        assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
//        assertThat(result.toString(), containsString("shoppingListId"));
//        assertThat(result.toString(), not(containsString("cocktails")));
    }
}
