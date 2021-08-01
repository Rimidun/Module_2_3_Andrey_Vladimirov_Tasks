package project;

import org.hibernate.SessionFactory;

public interface Context {

    Object getBean(String name);
    SessionFactory getSessionFactory();
}