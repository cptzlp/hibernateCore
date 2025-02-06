package by.max.hibernate.entity;

import by.max.hibernate.converter.BirthdayConverter;
import javax.persistence.*;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "company")
@EqualsAndHashCode(of = "username")
@Builder
@Entity
@Table(name = "users", schema = "hibernate")
public class User {
    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;
    @Embedded
    private PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
