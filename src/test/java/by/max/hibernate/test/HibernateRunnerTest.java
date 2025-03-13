package by.max.hibernate.test;

import by.max.hibernate.dao.UserDao;
import by.max.hibernate.entity.*;




import by.max.hibernate.util.HibernateUtil;
import lombok.Cleanup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.time.Instant;


public class HibernateRunnerTest {
    @Test
    public void findAllByCompanyName(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserDao userDao = UserDao.getInstance();

        System.out.println(userDao.findAllByCompanyName(session, "Amazon"));


        session.getTransaction().commit();
    }

    @Test
    public void findLimitedUsersOrderedByBirthday(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserDao userDao = UserDao.getInstance();

        System.out.println(userDao.findLimitedUsersOrderedByBirthday(session, 1));


        session.getTransaction().commit();
    }


    @Test
    public void findByFirstName() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserDao userDao = UserDao.getInstance();

        System.out.println(userDao.findByFirstName(session, "Ivan"));


        session.getTransaction().commit();
    }
    @Test
    public void findAll() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserDao userDao = UserDao.getInstance();

        System.out.println(userDao.findAll(session));

        Assertions.assertEquals(2, userDao.findAll(session).size());


        session.getTransaction().commit();
    }


    @Test
    public void checkHQL() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        String name = "Ivan";
        String company = "Amazon";



        session.getTransaction().commit();
    }


    @Test
    public void checkInheritance() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder().name("Google").build();

        session.save(company);


        session.flush();
        session.clear();


        session.getTransaction().commit();
    }

    @Test
    public void checkH2() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder().name("Google").build();

        session.save(company);


        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Chat chat = session.get(Chat.class, 1L);
        User user = session.get(User.class, 3L);

        UserChat userChat = new UserChat();

        userChat.setCreatedAt(Instant.now());
        userChat.setCreatedBy("Maxim");

        userChat.setChat(chat);
        userChat.setUser(user);

        session.save(userChat);


        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = User.builder()
                .username("ivan100@gmail.com")
                .build();

        Profile profile = Profile.builder()
                .street("BikerStreet")
                .language("EN")
                .build();

        session.save(user);
        profile.setUser(user);
        session.save(profile);
        session.getTransaction().commit();

    }

    @Test
    public void checkOrphanRemoval() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 1);
        company.getUsers().removeIf(user -> user.getId().equals(4L));


        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("Amazon")
                .build();

        User user = User.builder()
                .username("ivan1@gmail.com")
                .company(company)
                .build();

        company.addUser(user);

        session.save(company);

        session.getTransaction().commit();
    }


    @Test
    public void checkOneToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 1);
        System.out.println(company.getUsers());

        session.getTransaction().commit();

    }

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException {
        /*User user = User.builder()
                .username("ivan2424@mail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthDate(new Birthday(LocalDate.of(2003, 2, 4)))
                .role(Role.ADMIN)
                .build();

        String sql = """
                insert into
                %s
                (%s)
                values
                (%s)
                """;

        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        String fieldNames = Arrays.stream(fields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName())
                ).collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(fields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "20062006");
        PreparedStatement preparedStatement = connection
                .prepareStatement(sql.formatted(tableName, fieldNames, columnValues));
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            preparedStatement.setObject(i + 1, fields[i].get(user));
        }
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();*/
    }
}
