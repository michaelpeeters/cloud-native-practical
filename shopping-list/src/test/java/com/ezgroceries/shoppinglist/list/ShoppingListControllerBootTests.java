package com.ezgroceries.shoppinglist.list;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * These tests run the ShoppingListController using the MockMVC framework.
 * The server does not need to be running.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingListController.class)
public class ShoppingListControllerBootTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    public void createShoppingList() throws Exception {
        ShoppingList testShoppingList = new ShoppingList("abc");
        // arrange
        given(shoppingListService.add(testShoppingList)).willReturn(testShoppingList);

        // act and assert
        mockMvc.perform(post("/shopping-lists").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"name\": \"my test list\"}")
                .characterEncoding("utf-8")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("name").value("my test list")).andExpect(jsonPath("shoppingListId").isString())
                .andExpect(jsonPath("cocktails").doesNotExist());

        // verify
        verify(shoppingListService).add(testShoppingList);

    }

}
