package project.repository.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import project.ApplicationContext;
import project.repository.GenericRepository;

import java.io.Serializable;
import java.util.List;

public abstract class GenericRepositoryImp<T, ID extends Serializable> implements GenericRepository<T, ID> {

    private static SessionFactory sessionFactory = ApplicationContext.init().getSessionFactory();
    protected final Class<T> entity;

    public GenericRepositoryImp(Class<T> entity) {
        this.entity = entity;
    }

    public abstract T getById(ID id);

    public abstract T getByName(String name);

    public abstract List<T> getAll();

    public abstract T save(T o);

    public abstract T update(T o);

    public abstract void remove(T o);

    protected Session getSession(){
        return sessionFactory.openSession();
    }
}
