package by.max.hibernate.entity;

import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter

public abstract class AuditableEntity<T extends Serializable> implements BaseEntity<Long>{

    private Instant createdAt;

    private String createdBy;
}
