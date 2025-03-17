package by.max.hibernate.entity;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cache;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@Builder
@Data
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "company")
public class Company implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;


    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}
