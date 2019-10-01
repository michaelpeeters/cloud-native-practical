package com.ezgroceries.shoppinglist.integration.cocktail;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.ezgroceries.shoppinglist.cocktail.CocktailController;
import com.ezgroceries.shoppinglist.integration.MyIntegrationTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(MyIntegrationTestConfiguration.class)

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
    public void allCocktails() throws Exception {

        ResponseEntity result = restTemplate.getForEntity(baseUrl + "/cocktails", String.class);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.toString(), containsString("cocktailId"));
        assertThat(result.toString(), containsString("Tequila"));
    }

    @Test
    public void searchCocktails() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/cocktails").queryParam("search", "russian");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.toString(), containsString("cocktailId"));
        assertThat(result.toString(), containsString("Russian"));
        assertThat(result.toString(), containsString("Cola"));
    }
}
