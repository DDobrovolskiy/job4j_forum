package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.models.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
