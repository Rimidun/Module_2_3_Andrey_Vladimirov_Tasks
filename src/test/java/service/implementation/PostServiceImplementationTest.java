package service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entity.Post;
import project.entity.Writer;
import project.repository.implementation.PostRepositoryImpl;
import project.service.implementation.PostServiceImpl;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplementationTest {

    @Mock
    private PostRepositoryImpl postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private Writer writer = new Writer();
    private Post post = new Post("Test content");

    @BeforeEach
    void setUp() {
        post.setId(99L);
        writer.setId(8L);
        post.setWriter(writer);
        post.setCreate(new Date());
        post.setUpdated(new Date());
    }

    @Test
    void save() {

        Mockito.when(postRepository.save(post)).thenAnswer(invocation -> {
            post.setId(10L);
            post.setCreate(new Date());
            post.setUpdated(new Date());
            return post;
        });

        Post result = postService.save(post);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getWriter());
        assertNotNull(result.getCreate());
        assertNotNull(result.getUpdated());
        assertNotNull(result.getContent());

        assertEquals(10L, result.getId());
        assertEquals(8L, result.getWriter().getId());
        assertEquals(post.getContent(), result.getContent());

        Mockito.verify(postRepository, Mockito.times(1)).save(Mockito.any(Post.class));

    }

    @Test
    void update() {
        Post updatePost = new Post("Update post");
        updatePost.setId(post.getId());
        Mockito.when(postRepository.update(updatePost)).thenAnswer(invocation -> {
            post.setContent(updatePost.getContent());
            return post;
        });

        Post result = postService.update(updatePost);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getWriter());
        assertNotNull(result.getCreate());
        assertNotNull(result.getUpdated());
        assertNotNull(result.getContent());

        assertEquals(99L, result.getId());
        assertEquals(8L, result.getWriter().getId());
        assertEquals("Update post", result.getContent());
        Mockito.verify(postRepository, Mockito.times(1)).update(Mockito.any(Post.class));
    }

    @Test
    void getById() {
        post.setId(99L);
        Mockito.when(postRepository.getById(99L)).thenReturn(post);

        Post result = postService.getById(99L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getWriter());
        assertNotNull(result.getCreate());
        assertNotNull(result.getUpdated());
        assertNotNull(result.getContent());

        assertEquals(99L, result.getId());
        assertEquals(8L, result.getWriter().getId());
        assertEquals(post.getContent(), result.getContent());

        Mockito.verify(postRepository, Mockito.times(1)).getById(Mockito.anyLong());
    }

    @Test
    void getByContent() {
        post.setId(99L);
        Mockito.when(postRepository.getByContent("Test content")).thenReturn(post);

        Post result = postService.getByContent("Test content");

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getWriter());
        assertNotNull(result.getCreate());
        assertNotNull(result.getUpdated());
        assertNotNull(result.getContent());

        assertEquals(99L, result.getId());
        assertEquals(8L, result.getWriter().getId());
        assertEquals(post.getContent(), result.getContent());

        Mockito.verify(postRepository, Mockito.times(1)).getByContent(Mockito.anyString());
    }

    @Test
    void getAll() {
        Mockito.when(postRepository.getAll()).thenReturn(Mockito.mock(List.class));

        List<Post> results = postService.getAll();

        assertNotNull(results);

        Mockito.verify(postRepository, Mockito.times(1)).getAll();
    }

    @Test
    void remove() {
        Mockito.when(postRepository.getById(post.getId())).thenReturn(null);

        boolean result = postService.remove(post);

        assertEquals(true, result);
        Mockito.verify(postRepository, Mockito.times(1)).remove(Mockito.any(Post.class));
    }
}