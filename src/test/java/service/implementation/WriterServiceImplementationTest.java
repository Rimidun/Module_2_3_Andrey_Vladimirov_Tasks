package service.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entity.Label;
import project.entity.Writer;
import project.repository.implementation.WriterRepositoryImpl;
import project.service.implementation.WriterServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class WriterServiceImplementationTest {

@Mock
private WriterRepositoryImpl writerRepository;

@InjectMocks
private WriterServiceImpl writerService;

private Writer writer;
private Label label;

@BeforeEach
    void setUp() {
            label = new Label();
            label.setId(10L);
            label.setName("Perm");

            writer = new Writer();
            writer.setId(5L);
            writer.setFirstName("Anton");
            writer.setLastName("Nazarov");
            writer.setLabel(label);
            writer.setPosts(new ArrayList<>());
        }

@Test
    void save() {
            Mockito.when(writerRepository.save(writer)).thenAnswer(invocation -> {
            writer.setId(55L);
            return writer;
            });

            Writer result = writerService.save(writer);

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getFirstName());
            assertNotNull(result.getLastName());
            assertNotNull(result.getLabel());
            assertNotNull(result.getPosts());

            assertEquals(55L, result.getId());

            Mockito.verify(writerRepository, Mockito.times(1)).save(Mockito.any(Writer.class));
        }

@Test
    void update() {
            Writer update = new Writer("Test", "Testov", label);
            update.setId(5L);
            Mockito.when(writerRepository.update(update)).thenAnswer(invocation -> {
            writer.setFirstName(update.getFirstName());
            writer.setLastName(update.getLastName());
            return writer;
            });

            Writer result = writerService.update(update);

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getFirstName());
            assertNotNull(result.getLastName());
            assertNotNull(result.getLabel());
            assertNotNull(result.getPosts());

            assertEquals(update.getId(), result.getId());
            assertEquals(update.getFirstName(), result.getFirstName());
            assertEquals(update.getLastName(), result.getLastName());

            Mockito.verify(writerRepository, Mockito.times(1)).update(Mockito.any(Writer.class));

        }

@Test
    void getById() {
            Mockito.when(writerRepository.getById(writer.getId())).thenReturn(writer);

            Writer result = writerService.getById(writer.getId());

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getFirstName());
            assertNotNull(result.getLastName());
            assertNotNull(result.getLabel());
            assertNotNull(result.getPosts());

            assertEquals(writer.getId(), result.getId());

            Mockito.verify(writerRepository, Mockito.times(1)).getById(Mockito.anyLong());
            }

@Test
    void getByFirstName() {
            Mockito.when(writerRepository.getByFirstName(Mockito.anyString())).thenReturn(writer);

            Writer result = writerService.getByFirstName("Ivan");

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getFirstName());
            assertNotNull(result.getLastName());
            assertNotNull(result.getLabel());
            assertNotNull(result.getPosts());

            assertEquals("Ivan", writer.getFirstName());

            Mockito.verify(writerRepository, Mockito.times(1)).getByFirstName(Mockito.anyString());
            }

@Test
    void getByLastName() {
            Mockito.when(writerRepository.getByLastName("Ivanov")).thenReturn(writer);

            Writer result = writerService.getByLastName("Ivanov");

            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(result.getFirstName());
            assertNotNull(result.getLastName());
            assertNotNull(result.getLabel());
            assertNotNull(result.getPosts());

            assertEquals("Ivanov", writer.getLastName());

            Mockito.verify(writerRepository, Mockito.times(1)).getByLastName(Mockito.anyString());
            }

@Test
    void getAll() {
            Mockito.when(writerRepository.getAll()).thenReturn(new ArrayList<>());

        List<Writer> results = writerService.getAll();

        assertNotNull(results);

        Mockito.verify(writerRepository, Mockito.times(1)).getAll();

        }

@Test
    void remove() {
            Mockito.when(writerRepository.getById(writer.getId())).thenReturn(null);

            boolean result = writerService.remove(writer);

            assertEquals(true, result);
            }
            }
