package project.repository;

import project.entity.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    Post getByContent(String content);
    List<Post> getByWriterId(Long writerId);

}
