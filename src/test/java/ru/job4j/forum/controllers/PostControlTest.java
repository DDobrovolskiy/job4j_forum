package ru.job4j.forum.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.services.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControlTest {

    @Autowired
    private PostService postService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postServiceIsNotNull() {
        Assert.assertNotNull(postService);
    }

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
        int postId = postService.save(Post.of("test", "test"));
        this.mockMvc
                .perform(get("/posts/" + postId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void whenGetPostJSPAndRedirect() throws Exception {
        int id = -1;
        this.mockMvc
                .perform(get("/posts/" + id))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/"));
    }
}