package by.max.hibernate;

import by.max.hibernate.dao.*;
import by.max.hibernate.dto.UserCreateDto;
import by.max.hibernate.dto.UserReadDto;
import by.max.hibernate.entity.*;
import by.max.hibernate.mapper.CompanyReadMapper;
import by.max.hibernate.mapper.UserCreateMapper;
import by.max.hibernate.mapper.UserReadMapper;
import by.max.hibernate.service.UserService;
import by.max.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.LockModeType;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {

            Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    ((proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)));
            session.beginTransaction();

            CompanyRepository companyRepository = new CompanyRepository(session);
            CompanyReadMapper companyReadMapper = new CompanyReadMapper();
            UserReadMapper userReadMapper = new UserReadMapper(companyReadMapper);
            UserCreateMapper userCreateMapper = new UserCreateMapper(companyRepository);

            UserRepository userRepository = new UserRepository(session);
            UserService userService = new UserService(userRepository, userReadMapper, userCreateMapper);

//            Long userId = userService.create(new UserCreateDto(PersonalInfo.builder()
//                    .firstname("Petr")
//                    .lastname("Petrov")
//                    .birthDate(new Birthday(LocalDate.now())).build(),
//                    "petya32@mail.ru",
//                    Role.USER,
//                    1));

            // Optional<UserReadDto> user = userService.findUserById(3L);
            UserCreateDto updatedUser = new UserCreateDto(PersonalInfo.builder()
                    .firstname("Max")
                    .lastname("Petrov")
                    .birthDate(new Birthday(LocalDate.now())).build(),
                    "petya32@mail.ru",
                    Role.USER,
                    1);

            userService.update(3L, updatedUser);

            //System.out.println(userId);

            session.getTransaction().commit();
        }
    }
}
