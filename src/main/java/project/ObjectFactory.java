package project;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ObjectFactory {
    private Reflections reflections = new Reflections("com.nazarov");
    private SessionFactory sessionFactory = null;

    private SessionFactory createSessionFactory(){
        if(this.sessionFactory != null){
            return this.sessionFactory;
        }
        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
        Configuration configuration = new Configuration();
        for (Class<?> entityClass : entityClasses) {
            configuration.addAnnotatedClass(entityClass);
        }
        this.sessionFactory = configuration.buildSessionFactory();
        return this.sessionFactory;
    }

    private Map<String, Object> getRepository(){
        Set<Class<?>> repositoryClasses = reflections.getTypesAnnotatedWith(MyRepository.class);
        Map<String, Object> repository = new HashMap<>();
        for (Class<?> repositoryClass : repositoryClasses) {
            try {
                repository.put(repositoryClass.getSimpleName(), repositoryClass.newInstance());
            } catch (InstantiationException e) {
                System.err.println(e.getMessage());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return repository;
    }

    public  Map<String, Object> getBeansMap(){
        Map<String, Object> beans = new HashMap<>();
        beans.put("sessionFactory", createSessionFactory());
        beans.putAll(getRepository());

        return beans;
    }

}
