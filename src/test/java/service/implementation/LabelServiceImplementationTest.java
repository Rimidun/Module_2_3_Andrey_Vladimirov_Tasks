package service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entity.Label;
import project.repository.implementation.LabelRepositoryImpl;
import project.service.implementation.LabelServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LabelServiceImplementationTest {

    @Mock
    private LabelRepositoryImpl labelRepository;

    @InjectMocks
    private LabelServiceImpl labelService;

    private Label testLabel = new Label(8L, "Tested label");
    private Label testLabel2 = new Label(null, "Saved test label");
    private Label updateTestLabel = new Label(testLabel.getId(), "Updated label");

    @Test
    void save() {
        Mockito.when(labelRepository.getByName(testLabel2.getName())).thenReturn(null);
        Mockito.when(labelRepository.save(testLabel2)).thenAnswer(invocation -> {
            testLabel2.setId(77L);
            return testLabel2;
        });
        Mockito.when(labelRepository.getByName(testLabel.getName())).thenReturn(testLabel);

        Label label = labelService.save(testLabel);
        assertNotNull(label);
        assertNotNull(label.getId());
        assertNotNull(label.getName());
        assertEquals(8L, testLabel.getId());
        assertEquals("Tested label", testLabel.getName());

        Label label2 = labelService.save(testLabel2);
        assertNotNull(label2);
        assertNotNull(label2.getId());
        assertNotNull(label2.getName());
        assertEquals(77L, testLabel2.getId());
        assertEquals("Saved test label", testLabel2.getName());

        labelService.save(null);

        Mockito.verify(labelRepository, Mockito.times(1)).save(Mockito.any(Label.class));
        Mockito.verify(labelRepository, Mockito.times(2)).getByName(Mockito.anyString());

    }

    @Test
    void update() {
        Mockito.when(labelRepository.update(updateTestLabel)).thenReturn(updateTestLabel);
        Mockito.when(labelRepository.getById(updateTestLabel.getId())).thenReturn(testLabel);
        Mockito.when(labelRepository.getById(99L)).thenReturn(null);

        Label result = labelService.update(updateTestLabel);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getName());
        assertEquals(result.getName(), updateTestLabel.getName());
        assertEquals(result.getId(), updateTestLabel.getId());

        Label result2 = labelService.update(updateTestLabel);
        assertEquals(updateTestLabel, result2);

        Label result3 = labelService.update(new Label(99L, "Some label"));
        assertNull(result3);

        Label result4 = labelService.update(null);
        assertNull(result4);

        Mockito.verify(labelRepository, Mockito.times(2)).update(Mockito.any(Label.class));
        Mockito.verify(labelRepository, Mockito.times(3)).getById(Mockito.anyLong());
    }

    @Test
    void getById() {
        Mockito.when(labelRepository.getById(Mockito.anyLong())).thenReturn(Mockito.mock(Label.class));
        Mockito.when(labelRepository.getById(8L)).thenReturn(testLabel);
        Mockito.when(labelRepository.getById(9L)).thenReturn(null);

        Label label = labelService.getById(7L);
        assertNotNull(label);

        Label label2 = labelService.getById(8L);
        assertNotNull(label2);
        assertNotNull(label2.getId());
        assertNotNull(label2.getName());
        assertEquals(testLabel.getId(), label2.getId());
        assertEquals(testLabel.getName(), label2.getName());

        Label label3 = labelService.getById(null);
        assertNull(label3);

        Label label4 = labelService.getById(9L);
        assertNull(label4);

        Mockito.verify(labelRepository, Mockito.times(3)).getById(Mockito.anyLong());

    }

    @Test
    void getByName() {
        Mockito.when(labelRepository.getByName(Mockito.anyString())).thenReturn(Mockito.mock(Label.class));
        Mockito.when(labelRepository.getByName(testLabel.getName())).thenReturn(testLabel);
        Mockito.when(labelRepository.getByName("Some name")).thenReturn(null);

        Label label = labelService.getByName("Some label");
        assertNotNull(label);

        Label label2 = labelService.getByName(testLabel.getName());
        assertNotNull(label2);
        assertNotNull(label2.getId());
        assertNotNull(label2.getName());
        assertEquals(testLabel.getId(), label2.getId());
        assertEquals(testLabel.getName(), label2.getName());

        Label label3 = labelService.getByName(null);
        assertNull(label3);

        Label label4 = labelService.getByName("Some name");
        assertNull(label4);

        Mockito.verify(labelRepository, Mockito.times(3)).getByName(Mockito.anyString());
    }

    @Test
    void remove() {
        Mockito.when(labelRepository.getById(testLabel.getId())).thenReturn(testLabel);

        boolean result = labelService.remove(null);
        assertEquals(false, result);

        boolean result2 = labelService.remove(testLabel);
        assertEquals(true, result2);

        boolean result3 = labelService.remove(new Label(10L, "Some label"));
        assertEquals(false, result3);

        Mockito.verify(labelRepository, Mockito.times(1)).remove(Mockito.any(Label.class));
        Mockito.verify(labelRepository, Mockito.times(2)).getById(Mockito.any(Long.class));
    }

}
