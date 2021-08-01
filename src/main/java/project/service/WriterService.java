package project.service;

import project.entity.Writer;

import java.util.List;

public interface WriterService {

    Writer save(Writer writer);
    Writer update(Writer writer);
    Writer getById(Long id);
    Writer getByFirstName(String firstName);
    Writer getByLastName(String lastName);
    List<Writer> getAll();
    boolean remove(Writer writer);

}
