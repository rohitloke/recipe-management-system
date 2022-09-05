package com.rms.assignment.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rms.assignment.api.AbstractControllerIntegrationTest;
import com.rms.assignment.core.entity.Recipe;
import com.rms.assignment.core.search.service.IndexingService;

public class RecipeControllerIntegrationTest extends AbstractControllerIntegrationTest {

    @Autowired
    private IndexingService indexingService;

    /**
     * Search Index has been tested extensively on a lower level in the
     * RecipeSearchRepositoryImplIntegrationTest so here we only cover happy path
     * and the serializationn/deserialization of the request reesponse.
     * 
     */
    @Test
    public void testSearch() throws Exception {
        indexingService.initiateIndexing();
        this.mockMvc.perform(get(
                "/recipe/search?instructions=\"cream\"&includeIngredients=\"oreo\"&excludeIngredients=\"ingredient\"&onlyVeg=true&servings=9"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Oreo Ice Cream Dessert"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Oreo dessert"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].veg").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serves").value("10"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].instructions").value(
                        "    Soften ice cream until you can mix it with other ingredients \r\n    Crumble Oreo cookies\r\n    Mix Oreo cookies and cool whip into ice cream\r\n    Blend well\r\n    Put into a flat freezer container\r\n    Freeze until ready to serve"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ingredients")
                        .value("    ½ gallon vanilla ice cream\r\n    1 pack Oreo cookies\r\n    8 oz. Cool whip\r\n"));
    }

    @Test
    public void testGetAll() throws Exception {
        this.mockMvc.perform(get("/recipe")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Oreo Ice Cream Dessert"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Oreo dessert"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].veg").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serves").value("10"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].instructions").value(
                        "    Soften ice cream until you can mix it with other ingredients \r\n    Crumble Oreo cookies\r\n    Mix Oreo cookies and cool whip into ice cream\r\n    Blend well\r\n    Put into a flat freezer container\r\n    Freeze until ready to serve"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ingredients")
                        .value("    ½ gallon vanilla ice cream\r\n    1 pack Oreo cookies\r\n    8 oz. Cool whip\r\n"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("other recipe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("main course"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].veg").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].serves").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].instructions").value("Other recipe preparation"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].ingredients").value("    ingredient 1   cookies 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serves").value("10"));
    }

    @Test
    public void testCreate() throws Exception {
        this.mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\"name\": \"new recipe\", \"description\":\"new description\", \"veg\":\"true\", \"serves\":\"3\", \"instructions\":\"do something\", \"ingredients\":\"add something\"}"))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("new recipe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("new description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.veg").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serves").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructions").value("do something"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ingredients").value("add something"));
        Recipe recipe = (Recipe) getEntityManagerFactory().createEntityManager()
                .createNativeQuery("Select * from recipes where name ='new recipe'", Recipe.class).getSingleResult();
        assertNotNull(recipe);
    }

    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(put("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\"name\": \"other recipe\", \"description\":\"new description\", \"veg\":\"false\", \"serves\":\"6\", \"instructions\":\"Other recipe preparation\", \"ingredients\":\"    ingredient 1   cookies 2\"}"))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("other recipe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("new description"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.veg").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serves").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instructions").value("Other recipe preparation"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ingredients").value("    ingredient 1   cookies 2"));
        Recipe recipe = (Recipe) getEntityManagerFactory().createEntityManager().find(Recipe.class, 1001L);
        assertNotNull(recipe);
        assertEquals("new description", recipe.getDescription());
    }

    @Test
    public void testUpdateNonExistent() throws Exception {
        this.mockMvc.perform(put("/recipe").contentType(MediaType.APPLICATION_JSON).content(
                "{\"name\": \"non existent\", \"description\":\"new description\", \"veg\":\"false\", \"serves\":\"6\", \"instructions\":\"Other recipe preparation\", \"ingredients\":\"    ingredient 1   cookies 2\"}"))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Entity not found"));
    }
}
