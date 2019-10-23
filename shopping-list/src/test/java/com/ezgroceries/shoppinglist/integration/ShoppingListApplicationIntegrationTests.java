package com.ezgroceries.shoppinglist.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.list.controller.ShoppingListController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class ShoppingListApplicationIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private ShoppingListController shoppingListController;
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders httpHeaders;

    private String baseUrl;

    @Before
    public void setup() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        baseUrl = "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
        assertThat(shoppingListController, is(notNullValue()));
    }

    @Test
    public void createNewShoppingList() throws Exception {
        HttpEntity<String> request = new HttpEntity<String>("{\"name\": \"Stephanie's birthday\"}", httpHeaders);

        ResponseEntity result = restTemplate.postForEntity(baseUrl + "/shopping-lists", request, String.class);
        assertThat(result.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(result.toString(), containsString("shoppingListId"));
        assertThat(result.toString(), not(containsString("cocktails")));
    }

    @Test
    public void getShoppingList() throws Exception {
        //  HttpEntity<String> request = new HttpEntity<String>( httpHeaders);

        ResponseEntity result = restTemplate.getForEntity(baseUrl + "/shopping-lists/16726782", String.class);
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

    }
}
