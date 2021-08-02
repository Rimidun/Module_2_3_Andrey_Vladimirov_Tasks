package project.repository.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entity.Label;
import project.entity.Writer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class WriterRepositoryImplementationTest {

    @InjectMocks
    private WriterRepositoryImpl writerRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByFirstName() {
        Writer result = writerRepository.getByFirstName("FirstName");

        assertNotNull(result);
        assertNotNull(result.getPosts());
        assertNotNull(result.getLabel());

        assertEquals(8L, result.getId());
        assertEquals("FirstName", result.getFirstName());
        assertEquals("LastName", result.getLastName());
    }

    @Test
    void findByLastName() {
        Writer result = writerRepository.getByLastName("LastName");

        assertNotNull(result);
        assertNotNull(result.getPosts());
        assertNotNull(result.getLabel());

        assertEquals(8L, result.getId());
        assertEquals("FirstName", result.getFirstName());
        assertEquals("LastName", result.getLastName());
    }

    @Test
    void findById() {
        Writer result = writerRepository.getById(8L);

        assertNotNull(result);
        assertNotNull(result.getPosts());
        assertNotNull(result.getLabel());

        assertEquals(8L, result.getId());
        assertEquals("FirstName", result.getFirstName());
        assertEquals("LastName", result.getLastName());
    }

    @Test
    void findByName_also_findByFirstName() {
    }

    @Test
    void findAll() {
        List<Writer> results = writerRepository.getAll();

        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    void save() {
        Writer writer = new Writer("FirstName", "LastName", new Label("Moscow"));

        writer = writerRepository.save(writer);

        assertNotNull(writer);
        assertNotNull(writer.getId());
        assertNotNull(writer.getFirstName());
        assertNotNull(writer.getLastName());
        assertNotNull(writer.getLabel());

        assertEquals("FirstName", writer.getFirstName());
        assertEquals("LastName", writer.getLastName());
        assertEquals("Oklahoma", writer.getLabel().getName());


    }

    @Test
    void update() {
        Writer update = new Writer();
        update.setId(8L);
        update.setFirstName("Ivan");
        update.setLastName("Ivanov");
        update.setLabel(new Label("Moscow"));

        Writer isUpdated = writerRepository.update(update);

        assertNotNull(isUpdated);

        assertEquals(8L, isUpdated.getId());
        assertEquals("Ivan", isUpdated.getFirstName());
        assertEquals("Ivanov", isUpdated.getLastName());
        assertEquals("Moscow", isUpdated.getLabel().getName());

        Writer oldWriter = new Writer("FirstName", "LastName", new Label("Moscow"));
        oldWriter.setId(8L);
        writerRepository.update(oldWriter);
    }

    @Test
    void remove() {
        Writer writer = new Writer();
        writer.setId(8L);
        writer.setFirstName("Ivan");
        writer.setLastName("Ivanov");
        writer.setLabel(new Label("Moscow"));
        writerRepository.remove(writer);
    }
}
