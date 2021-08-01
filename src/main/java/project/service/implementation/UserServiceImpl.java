package project.service.implementation;


import project.entity.Writer;
import project.service.LabelService;
import project.service.PostService;
import project.service.UserService;
import project.service.WriterService;

public class UserServiceImpl implements UserService {
    private final LabelService labelService;
    private final PostService postService;
    private final WriterService writerService;

    public UserServiceImpl() {
        this.labelService = new LabelServiceImpl();
        this.postService = new PostServiceImpl();
        this.writerService = new WriterServiceImpl();
    }

    @Override
    public Writer save(Writer writer) {
        return writerService.save(writer);
    }

    @Override
    public Writer update(Writer writer) {
        return writerService.update(writer);
    }

    @Override
    public Writer get(Long id) {
        Writer result = writerService.getById(id);
        result.setPosts(postService.getByWriterId(result.getId()));
        result.setLabel(labelService.getById(result.getLabel().getId()));

        return result;
    }

    public Writer getByFirstName(String firstName) {
        Writer result = writerService.getByFirstName(firstName);
        result.setPosts(postService.getByWriterId(result.getId()));
        result.setLabel(labelService.getById(result.getLabel().getId()));

        return result;
    }

    public Writer getByLastName(String lastName) {
        Writer result = writerService.getByLastName(lastName);
        result.setPosts(postService.getByWriterId(result.getId()));
        result.setLabel(labelService.getById(result.getLabel().getId()));

        return result;
    }

    @Override
    public boolean remove(Writer writer) {
        return writerService.remove(writer);
    }
}