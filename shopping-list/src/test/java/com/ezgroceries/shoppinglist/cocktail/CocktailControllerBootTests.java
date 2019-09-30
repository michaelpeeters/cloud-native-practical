package com.ezgroceries.shoppinglist.cocktail;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * These tests run the CocktailController using the MockMVC framework.
 * The server does not need to be running.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CocktailController.class)
public class CocktailControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    @Test
    public void getAll() throws Exception {

        // arrange
        given(cocktailService.getAll()).willReturn(dummyListOfCocktails());

        // act and assert
        mockMvc.perform(get("/cocktails")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(Matchers.containsString("23b3d85a-3928-41c0-a533-6538a71e17c4")))
                .andExpect(content().string(Matchers.containsString("d615ec78-fe93-467b-8d26-5d26d8eab073")));

        // verify
        verify(cocktailService).getAll();

    }

    private List<Cocktail> dummyListOfCocktails() {
        return Arrays.asList(new Cocktail(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita", "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the " + "salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                new Cocktail(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita", "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
    }

}
