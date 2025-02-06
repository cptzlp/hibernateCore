package by.max.hibernate.entity;


import javax.persistence.*;

import lombok.*;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@Builder
@Data
@Entity
@Table(schema = "hibernate", name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;


    @OneToMany(mappedBy = "company")
    private Set<User> users;
}
