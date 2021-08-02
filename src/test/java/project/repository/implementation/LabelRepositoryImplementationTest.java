package project.repository.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import project.ApplicationContext;
import project.Context;
import project.entity.Label;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LabelRepositoryImplementationTest {

    @InjectMocks
    private LabelRepositoryImpl labelRepository;

    private Label testLabel;

    @BeforeEach
    void setUp() {
        Context context = ApplicationContext.init();
        testLabel = new Label(null, "Тэг для теста");
        testLabel = labelRepository.save(testLabel);
    }

    @Test
    void findById() {
        Label truLabel = labelRepository.getById(testLabel.getId());

        assertNotNull(truLabel);
        assertNotNull(truLabel.getId());
        assertNotNull(truLabel.getName());
        assertEquals(testLabel.getId(), truLabel.getId());

        Label falseLabel = labelRepository.getById(101L);

        assertNull(falseLabel);
    }


    @Test
    void findByName() {
        Label truLabel = labelRepository.getByName(testLabel.getName());

        assertNotNull(truLabel);
        assertNotNull(truLabel.getId());
        assertNotNull(truLabel.getName());
        assertEquals(testLabel.getName(), truLabel.getName());

        Label falseLabel = labelRepository.getByName("Some_Name");

        assertNull(falseLabel);
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
        Label label = new Label("Тест тэг");
        label = labelRepository.save(label);

        assertNotNull(label);
        assertNotNull(label.getId());
        assertNotNull(label.getName());
        assertEquals("Тест тэг", label.getName());
    }

    @Test
    void update() {

        Label updated = new Label(testLabel.getId(), "Тэг изменен");
        try {
            updated = labelRepository.update(updated);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(updated);
        assertEquals(testLabel.getId(), updated.getId());
        assertNotEquals(testLabel.getName(), updated.getName());
        assertNotEquals(testLabel, updated);

        try {
            updated = labelRepository.update(testLabel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(updated);
        assertEquals(testLabel, updated);
        assertEquals(testLabel.getId(), updated.getId());
        assertEquals(testLabel.getName(), updated.getName());
    }

    @Test
    void remove() {
        labelRepository.remove(testLabel);

        Label label = labelRepository.getById(testLabel.getId());

        assertNull(label);
    }
}
