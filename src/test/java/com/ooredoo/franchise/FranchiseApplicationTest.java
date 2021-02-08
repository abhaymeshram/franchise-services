package com.ooredoo.franchise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooredoo.franchise.api.FranchiseController;
import com.ooredoo.franchise.entity.Franchise;
import com.ooredoo.franchise.service.FranchiseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FranchiseController.class)
public class FranchiseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FranchiseService franchiseService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void getAllShouldReturnEmptyResponse() throws Exception {
        when(franchiseService.getAll()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/v1/getAll")).andExpect(status().isOk());
    }

    @Test
    public void getAllWithData() throws Exception {
        when(franchiseService.getAll()).thenReturn(getFranchiseData());
        this.mockMvc.perform(get("/v1/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)));
    }

    @Test
    public void saveFranchiseWihtBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/save")
                .content(asJsonString(new Franchise("", "", "")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveFranchise() throws Exception {
        Franchise franchise = new Franchise("Test", "1244", "abc@gmail.com");

        when(franchiseService.save(franchise)).thenReturn(franchise);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/save")
                .content(asJsonString(franchise))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFranchise() throws Exception {
        Franchise franchise = new Franchise("Test", "1244", "abc@gmail.com");

        when(franchiseService.update(franchise)).thenReturn(franchise);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/update")
                .content(asJsonString(franchise))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private List<Franchise> getFranchiseData() {
        List<Franchise> franchiseList = new ArrayList<>();
        franchiseList.add(new Franchise());
        franchiseList.add(new Franchise());
        franchiseList.add(new Franchise());
        return franchiseList;
    }
}
