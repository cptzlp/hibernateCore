package by.max.hibernate.dao;

import by.max.hibernate.entity.Payment;
import by.max.hibernate.entity.QPayment;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static by.max.hibernate.entity.QPayment.*;

public class PaymentRepository extends BaseRepository<Long, Payment> {


    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }

    public List<Payment> findAllByReceiverId(Long receiverId) {
        return new JPAQuery<Payment>(getEntityManager())
                .select(payment)
                .from(payment)
                .where(payment.receiver().id.eq(receiverId))
                .fetch();
    }
}
