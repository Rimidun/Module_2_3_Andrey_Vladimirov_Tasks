package project.service;

import project.entity.Label;

public interface LabelService {

    Label save(Label label);
    Label update(Label label);
    Label getById(Long id);
    Label getByName(String name);
    boolean remove(Label label);



}
