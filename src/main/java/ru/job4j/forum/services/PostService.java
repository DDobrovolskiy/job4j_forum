package ru.job4j.forum.services;

import org.springframework.stereotype.Service;
import ru.job4j.forum.models.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        List<Post> posts = new LinkedList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    public Optional<Post> findPostById(int id) {
        return postRepository.findById(id);
    }

    public int save(Post post) {
        if ((post.getId() < 0) || (post.getId() > postRepository.count())) {
            post.setId(0);
        }
        var postSave = postRepository.save(post);
        return postSave.getId();
    }
}
