package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

public class DbHelper extends HelperBase{

  private SessionFactory sessionFactory;

  public DbHelper(ApplicationManager app) {
    super(app);
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public List<User> users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> result = session.createQuery("from User where enabled = '1'").list();
    session.getTransaction().commit();
    session.close();
    return result;
  }

}
