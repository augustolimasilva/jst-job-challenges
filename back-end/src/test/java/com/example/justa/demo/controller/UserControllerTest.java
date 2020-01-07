package com.example.justa.demo.controller;

import com.example.justa.demo.model.User;
import com.example.justa.demo.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Test
    public void insertUser() throws Exception{
        User userMock = new User();

        userMock.setUsername("Augusto Silva");
        userMock.setEmail("augusto_ls17@hotmail.com");
        userMock.setPassword("augusto1993");

        Mockito.when(userService.insert(Mockito
                .any(User.class)))
                .thenReturn(userMock);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/user")
                .content(asJsonString(userMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void alterUser() throws Exception{
        User userMock = new User();

        userMock.setUsername("Augusto Lima da Silva");
        userMock.setEmail("augusto_ls17@hotmail.com");
        userMock.setPassword("augusto1993");

        Mockito.when(userService.alter(Mockito
                .any(User.class), Mockito.anyLong()))
                .thenReturn(userMock);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/user/{id}",1)
                .content(asJsonString(userMock))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/user/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/{id}",1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllUsers() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
