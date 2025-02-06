package by.max.hibernate.test;

import by.max.hibernate.entity.Birthday;
import by.max.hibernate.entity.Company;
import by.max.hibernate.entity.Role;
import by.max.hibernate.entity.User;

import javax.persistence.Column;
import javax.persistence.Table;

import by.max.hibernate.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.SessionFactoryServiceContributor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class HibernateRunnerTest {

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
