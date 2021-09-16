package ru.job4j.forum.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.services.PostService;

import java.util.ArrayList;

@Controller
@Slf4j
public class PostControl {
    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"/", "/posts"})
    public String index(Model model) {
        log.info("User: {}",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute(
                "user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("posts", postService.getAll());
        return "index";
    }

    @GetMapping("/posts/{id}/edit")
    public String getPostEdit(@PathVariable("id") int id, Model model) {
        //TODO проверка что пост принадлежит этому пользователю или админ
        var post = postService.findPostById(id).orElse(new Post());
        post.setId(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String postPostEdit(@PathVariable("id") int id, @ModelAttribute Post post) {
        post.setId(id);
        int realId = postService.save(post);
        return "redirect:/posts/" + realId;
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") int id, Model model) {
        var post = postService.findPostById(id);
        if (post.isPresent()) {
            model.addAttribute("topic", post.get());
            model.addAttribute("posts", new ArrayList<>());
            return "post";
        } else {
            log.info("Post not find, id = {}", id);
            return "redirect:/posts/";
        }
    }
}
