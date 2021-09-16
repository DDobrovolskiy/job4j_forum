package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        var post = Post.of("Продаю машину ладу 01.", "Орон-дон-дон");
        post.setId(1);
        posts.add(post);
    }

    public List<Post> getAll() {
        return posts;
    }

    public Optional<Post> findPostById(int id) {
        return posts.stream().filter(post -> post.getId() == id).findAny();
    }

    public int save(Post post) {
        //TODO проверить что ид не выходит за границу 0 and COUNT
        posts.add(post);
        return post.getId();
    }
}
