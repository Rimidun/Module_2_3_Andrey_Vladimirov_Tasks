package project.controller;

import project.entity.Writer;
import project.service.WriterService;
import project.service.implementation.WriterServiceImpl;

import java.util.List;

public class WriterController {
    private final WriterService writerService;

    public WriterController() {
        this.writerService = new WriterServiceImpl();
    }

    public Writer save(Writer writer){
        return writerService.save(writer);
    }

    public Writer update(Writer writer){
        return writerService.update(writer);
    }

    public Writer getById(Long id){
        return writerService.getById(id);
    }

    public Writer getByFirstName(String firstName){
        return writerService.getByFirstName(firstName);
    }

    public Writer getByLastName(String lastName){
        return writerService.getByLastName(lastName);
    }

    public List<Writer> getAll(){
        return writerService.getAll();
    }

    public boolean remove(Writer writer){
        return writerService.remove(writer);
    }
}
