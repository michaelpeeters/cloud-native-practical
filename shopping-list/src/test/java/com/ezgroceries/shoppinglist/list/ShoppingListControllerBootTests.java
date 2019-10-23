package com.ezgroceries.shoppinglist.list;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.cocktail.model.Cocktail;
import com.ezgroceries.shoppinglist.cocktail.service.CocktailService;
import com.ezgroceries.shoppinglist.list.controller.ShoppingListController;
import com.ezgroceries.shoppinglist.list.service.ShoppingListService;
import com.ezgroceries.shoppinglist.list.model.ShoppingList;
import java.util.Arrays;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * These tests run the ShoppingListController using the MockMVC framework.
 * The server does not need to be running.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingListController.class)
@Ignore
public class ShoppingListControllerBootTests {



    private final static UUID DUMMY_UUID = UUID.fromString("23b3d85a-1234-5678-1234-6538a71e17c4");
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private ShoppingListService shoppingListService;
    @SpyBean
    private CocktailService cocktailService;
    private ShoppingList testShoppingList;
    private Cocktail testCocktail;

    @Before
    public void setup() {
        testShoppingList = new ShoppingList("abc");
        testCocktail = new Cocktail(DUMMY_UUID, "my-cocktail", "glass", "instructions", "image", null);
    }

    @Test
    public void createShoppingList() throws Exception {
        // arrange

        //when(shoppingListService.add(any())).thenReturn(testShoppingList);

        // act and assert
        mockMvc.perform(post("/shopping-lists").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"name\": \"my test list\"}")
                .characterEncoding("utf-8")).andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("name").value("my test list")).andExpect(jsonPath("shoppingListId").isString())
                .andExpect(jsonPath("cocktails").doesNotExist());

        // verify
        verify(shoppingListService).add(any());
    }

    @Test
    public void addCocktailsToExistingShoppingList() throws Exception {
        ShoppingList testShoppingList = new ShoppingList("abc");
        // arrange
        when(shoppingListService.get(any())).thenReturn(testShoppingList);

        // act and assert
        mockMvc.perform(post("/shopping-lists/{shopping-list-id}/cocktails", testShoppingList.getShoppingListId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("[" + "{\"cocktailId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"},"
                        + "{\"cocktailId\": \"d615ec78-fe93-467b-8d26-5d26d8eab073\"}" + "]").characterEncoding("utf-8")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(content().string(Matchers.containsString("cocktailId")));

        // verify
        verify(shoppingListService).get(any());
        verify(cocktailService, times(2)).get(any());

    }

    @Test
    public void addCocktailToNonExistingShoppingList() throws Exception {
        // arrange
        // given(shoppingListService.get("non-existing-shopping-list")).willReturn(null);
        // given(cocktailService.get("my-cocktail")).willReturn(null);

        // act and assert
        mockMvc.perform(post("/shopping-lists/{shopping-list-id}/cocktails", DUMMY_UUID.toString()).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("[" + "{\"cocktailId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"},"
                        + "{\"cocktailId\": \"d615ec78-fe93-467b-8d26-5d26d8eab073\"}" + "]").characterEncoding("utf-8"))
                .andExpect(status().isNotFound()).andExpect(status().reason(Matchers.containsString("No such ShoppingList")));

        // verify
        verify(shoppingListService).get(any());
        verifyZeroInteractions(cocktailService);

    }

    @Test
    public void addNonExistiongCocktailsToShoppingList() throws Exception {
        ShoppingList testShoppingList = new ShoppingList("abc");
        // arrange
        when(shoppingListService.get(any())).thenReturn(testShoppingList);

        // act and assert
        mockMvc.perform(post("/shopping-lists/{shopping-list-id}/cocktails", testShoppingList.getShoppingListId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8).content("[" + "{\"cocktailId\": \"" + DUMMY_UUID.toString() + "\"}]")
                .characterEncoding("utf-8")).andExpect(status().isNotFound()).andExpect(status().reason(Matchers.containsString("No such Cocktail")));

        // verify
        verify(shoppingListService).get(any());
        verify(cocktailService).get(any());

    }

    @Test
    public void getShoppingList() throws Exception {
        ShoppingList testShoppingList = new ShoppingList("abc");
        // arrange
        when(shoppingListService.get(any())).thenReturn(testShoppingList);

        // act and assert
        mockMvc.perform(get("/shopping-lists/{shopping-list-id}", testShoppingList.getShoppingListId().toString())).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("name").value("abc"))
                .andExpect(jsonPath("shoppingListId").exists()).andExpect(jsonPath("ingredients").isArray());

        // verify
        verify(shoppingListService).get(any());
    }

    @Test
    public void getAllShoppingList() throws Exception {
        ShoppingList testShoppingList = new ShoppingList("abc");
        ShoppingList testShoppingList2 = new ShoppingList("abc2");
        // arrange
        when(shoppingListService.getAll()).thenReturn(Arrays.asList(testShoppingList, testShoppingList2));

        ///$[0] access result of toplevel array
        // act and assert
        mockMvc.perform(get("/shopping-lists")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].shoppingListId").value(not(isEmptyString()))).andExpect(jsonPath("$[0].name").value("abc"))
                .andExpect(jsonPath("$[0].ingredients").isArray()).andExpect(jsonPath("$[1].shoppingListId").value(not(isEmptyString())))
                .andExpect(jsonPath("$[1].name").value("abc2")).andExpect(jsonPath("$[1].ingredients").isArray());
        // verify
        verify(shoppingListService).getAll();
    }

}
