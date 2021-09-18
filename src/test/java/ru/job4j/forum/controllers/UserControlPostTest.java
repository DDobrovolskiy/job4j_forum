package ru.job4j.forum.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.models.User;
import ru.job4j.forum.services.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControlPostTest {

    @Autowired
    private UserControl userControl;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registrationIsTrue() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        when(userService.registration(user)).thenReturn(true);

        mockMvc.perform(post("/reg")
                    .param("username", user.getUsername())
                    .param("password", user.getPassword()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void registrationIsFalse() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        when(userService.registration(user)).thenReturn(false);

        mockMvc.perform(post("/reg")
                .param("username", user.getUsername())
                .param("password", user.getPassword()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }
}