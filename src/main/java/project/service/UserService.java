package project.service;

import project.entity.Writer;


public interface UserService {

    Writer save(Writer writer);
    Writer update(Writer writer);
    Writer get(Long id);
    boolean remove(Writer writer);

    Writer getByFirstName(String firstName);
    Writer getByLastName(String lastName);
}
