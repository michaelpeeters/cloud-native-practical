package com.ezgroceries.shoppinglist.integration.cocktail;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.cocktail.CocktailController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CocktailIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private CocktailController cocktailController;
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
        assertThat(cocktailController, is(notNullValue()));
    }

    @Test
    public void createNewShoppingList() throws Exception {

        ResponseEntity result = restTemplate.getForEntity(baseUrl + "/cocktails", String.class);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.toString(), containsString("cocktailId"));
        assertThat(result.toString(), containsString("Tequila"));
    }
}
