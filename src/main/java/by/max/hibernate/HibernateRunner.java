package by.max.hibernate;

import by.max.hibernate.dao.UserDao;
import by.max.hibernate.entity.*;
import by.max.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.LockModeType;
import java.time.LocalDate;


@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Payment payment = session.find(Payment.class, 1L);

            payment.setAmount(payment.getAmount() + 50);

            session.getTransaction().commit();
        }


    }
}
