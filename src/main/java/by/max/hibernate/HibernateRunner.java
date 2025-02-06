package by.max.hibernate;


import by.max.hibernate.entity.*;
import by.max.hibernate.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                Company company = session1.get(Company.class, 2);

                User user = User.builder()
                        .username("ivan5@gmail.com")
                        .personalInfo(PersonalInfo.builder()
                                .firstname("Ivan")
                                .lastname("Ivanov")
                                .birthDate(new Birthday(LocalDate.of(2004, 2, 7)))
                                .build())
                        .role(Role.USER)
                        .company(company)
                        .build();

                //session1.save(company);
                session1.saveOrUpdate(user);


                session1.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
    }
}
