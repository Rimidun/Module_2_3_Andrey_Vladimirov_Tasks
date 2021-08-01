package project.controller;

import project.entity.Writer;
import project.service.UserService;
import project.service.implementation.UserServiceImpl;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    public Writer save(Writer writer){
        return userService.save(writer);
    }

    public Writer update(Writer writer){
        return userService.update(writer);
    }

    public Writer get(Long id){
        return userService.get(id);
    }

    public Writer getByFirstName(String firstName){

        return userService.getByFirstName(firstName);
    }

    public Writer getByLastName(String lastName){
        return userService.getByLastName(lastName);
    }

    public boolean remove(Writer writer){
        return userService.remove(writer);
    }
}
