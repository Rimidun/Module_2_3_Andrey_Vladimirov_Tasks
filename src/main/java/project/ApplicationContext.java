package project;

import org.hibernate.SessionFactory;

import java.util.Map;

public final class ApplicationContext implements Context {
    private static ApplicationContext INSTANCE;
    private final Map<String, Object> beans;

    public ApplicationContext() {
        this.beans = new ObjectFactory().getBeansMap();
    }


    @Override
    public Object getBean(String name) {
        return beans.get(name);
    }

    @Override
    public SessionFactory getSessionFactory(){
        return (SessionFactory) getBean("sessionFactory");
    }

    public static ApplicationContext init(){
        if(INSTANCE == null){
            synchronized (ApplicationContext.class){
                if(INSTANCE == null){
                    INSTANCE = new ApplicationContext();
                }
            }
        }

        return INSTANCE;
    }
}
