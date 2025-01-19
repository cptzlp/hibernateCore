package by.max.hibernate;

import by.max.hibernate.converter.BirthdayConverter;
import by.max.hibernate.entity.Birthday;
import by.max.hibernate.entity.Role;
import by.max.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = User.builder().username("ivan1313@mail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2003, 2, 4)))
                    .role(Role.ADMIN)
                    .build();

            //session.save(user);
            //session.update(user);
            //session.saveOrUpdate(user);
            //session.delete(user);
            User user1 = session.get(User.class, "ivan1313@mail.com");
            System.out.println(user1.toString());
            session.getTransaction().commit();
        }
    }
}
