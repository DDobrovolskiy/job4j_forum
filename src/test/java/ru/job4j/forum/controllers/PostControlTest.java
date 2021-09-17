package ru.job4j.forum.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenGetIndexJSP() throws Exception {
        this.mockMvc
                .perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    public void whenGetEditJSP() throws Exception {
        this.mockMvc
                .perform(get("/posts/0/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void whenGetPostJSP() throws Exception {
        this.mockMvc
                .perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }
}