package project.controller;

import project.entity.Label;
import project.service.LabelService;
import project.service.implementation.LabelServiceImpl;

public class LabelController {
    private final LabelService labelService;

    public LabelController() {
        this.labelService = new LabelServiceImpl();
    }

    public Label save(Label label){
        return labelService.save(label);
    }
    public Label update(Label label){
        return labelService.update(label);
    }
    public Label getById(Long id){
        return labelService.getById(id);
    }
    public Label getByName(String name){
        return labelService.getByName(name);
    }
    public boolean remove(Label label){
        return labelService.remove(label);
    }
}
