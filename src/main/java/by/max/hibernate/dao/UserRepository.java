package by.max.hibernate.dao;

import by.max.hibernate.entity.User;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class UserRepository extends BaseRepository<Long, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
