package project.controller;

import project.entity.Post;
import project.service.PostService;
import project.service.implementation.PostServiceImpl;

import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController() {
        this.postService = new PostServiceImpl();
    }

    public Post save(Post post) {
        return postService.save(post);
    }

    public Post update(Post post) {
        return postService.update(post);
    }

    public Post getById(Long id) {
        return postService.getById(id);
    }

    public Post getByContent(String content) {
        return postService.getByContent(content);
    }

    public List<Post> getByWriterId(Long writerId) {
        return postService.getByWriterId(writerId);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

    public boolean remove(Post post) {
        return postService.remove(post);
    }
}
