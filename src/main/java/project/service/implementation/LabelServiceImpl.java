package project.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.ApplicationContext;
import project.entity.Label;
import project.repository.LabelRepository;
import project.repository.implementation.LabelRepositoryImpl;
import project.service.LabelService;

public class LabelServiceImpl implements LabelService {
    private final Logger log = LoggerFactory.getLogger(LabelRepositoryImpl.class);

    private final LabelRepository labelRepository;

    public LabelServiceImpl() {
        this.labelRepository = (LabelRepositoryImpl) ApplicationContext.init().getBean("LabelRepositoryImpl");
    }

    // This constructor is test only @param labelRepository is Mock

    @Deprecated
    private LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label save(Label label) {
        if (label == null) {
            log.warn("IN - save - label is null.");
            return null;
        }

        Label find = getByName(label.getName());
        if (find == null) {
            return labelRepository.save(label);
        } else return find;
    }

    @Override
    public Label update(Label label) {
        if (label == null) {
            log.warn("IN - update - label is null.");
            return null;
        }

        Label find = labelRepository.getById(label.getId());
        if (find == null) {
            log.warn("IN - update - label on id:{} not found", label.getId());
            return null;
        } else if (find.equals(label)) {
            return find;
        }

        return labelRepository.update(label);
    }

    @Override
    public Label getById(Long id) {
        if (id == null) {
            log.info("IN - getById - id is null.");
            return null;
        }

        Label find = labelRepository.getById(id);
        if (find == null) {
            log.warn("IN - getById - label on id:{} not found.", id);
        }

        return find;
    }

    @Override
    public Label getByName(String name) {
        if (name == null) {
            log.warn("IN - getByName - name is null.");
            return null;
        }
        Label find = labelRepository.getByName(name);
        if (find == null) {
            log.warn("IN - getByName - label on name:\'{}\' not found.", name);
        }

        return find;
    }

    @Override
    public boolean remove(Label label) {
        if (label == null) {
            log.warn("IN - remove - label is null.");
            return false;
        }

        Label find = labelRepository.getById(label.getId());
        if (find != null) {
            labelRepository.remove(label);
            return true;
        } else return false;
    }
}