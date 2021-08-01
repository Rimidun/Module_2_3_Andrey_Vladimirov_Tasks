package project.repository.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entity.Post;
import project.entity.Writer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryImplementationTest {
    private Writer writer;

    @InjectMocks
    private PostRepositoryImpl postRepository;

    @BeforeEach
    void setUp() {
        writer = new Writer();
        writer.setId(8L);
    }

    @Test
    void findByName_also_findByContent() {
        Post result = postRepository.getByName("Single saving");

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getContent());
        assertNotNull(result.getCreate());
        assertNotNull(result.getUpdated());
        assertNotNull(result.getWriter());

        assertEquals("Single saving", result.getContent());
    }

    @Test
    void findByContent_also_findByName() {
    }

    @Test
    void findById() {
        Post result = postRepository.getById(6L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getContent());
        assertNotNull(result.getCreate());
        assertNotNull(result.getUpdated());
        assertNotNull(result.getWriter());

        assertEquals(6L, result.getId());
    }

    @Test
    void findAll() {
        List<Post> result = postRepository.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void save() {
        Post post = new Post("Single saving");
        post.setWriter(writer);
        post = postRepository.save(post);

        assertNotNull(post);
        assertNotNull(post.getId());
        assertNotNull(post.getWriter());
        assertNotNull(post.getContent());
        assertNotNull(post.getCreate());
        assertNotNull(post.getUpdated());

        assertEquals(post.getContent(), "Single saving");
        assertEquals(post.getWriter(), writer);
    }

    @Test
    void update() {
        Post post = new Post("Content for update");
        post.setId(6L);
        post.setWriter(writer);
        post = postRepository.update(post);

        assertNotNull(post);
        assertNotNull(post.getId());
        assertNotNull(post.getWriter());
        assertNotNull(post.getContent());
        assertNotNull(post.getUpdated());

        assertNotEquals(post.getCreate(), post.getUpdated());
        assertNotEquals("Single saving", post.getContent());

        assertEquals(6L, post.getId());
        assertEquals(writer.getId(), post.getWriter().getId());

        post.setContent("Single saving");
        post = postRepository.update(post);
    }

    @Test
    void remove() {
        Post post = new Post();
        post.setId(6L);
        postRepository.remove(post);
    }
}
