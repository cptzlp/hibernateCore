package by.max.hibernate.dao;



import by.max.hibernate.entity.User;


import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;



import java.util.List;

import static by.max.hibernate.entity.QCompany.*;
import static by.max.hibernate.entity.QUser.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session).select(user).from(user).fetch();
    }

    public List<User> findByFirstName(Session session, String firstName) {
        return new JPAQuery<User>(session).select(user).from(user).where(user
                .personalInfo().firstname.eq(firstName)).fetch();
    }

    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit){
        return new JPAQuery<User>(session).select(user).from(user)
                .orderBy(new OrderSpecifier(Order.ASC, user.personalInfo().birthDate))
                .limit(limit)
                .fetch();
    }

    public List<User> findAllByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session).select(user).from(company)
                .join(company.users, user)
                .where(company.name.eq(companyName)).fetch();
    }


}
