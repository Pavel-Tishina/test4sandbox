package com.itgnostic.test4sandbox.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.itgnostic.test4sandbox.common.Const.DB_TABLE_NAME;

@NoArgsConstructor
@Entity
@Table(
        name = DB_TABLE_NAME
        //indexes = {@Index(name = "const", columnList = "id, created")}
)
public class EmployeeEntity {
    @Getter
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Getter @Setter @Column(name="FIRST_NAME", nullable = false)
    private String firstName;
    @Getter @Setter @Column(name="LAST_NAME", nullable = false)
    private String lastName;
    @Getter @Setter @Column(name="A_POSITION")
    private String position;
    @Getter @Setter @Column(name="SUPERVISOR")
    private Long supervisor;
    @Getter @Setter @Column(name="SUBORDINATES", nullable = false)
    private Set<Long> subordinates = new HashSet<>();

    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE", nullable = false, updatable = false)
    private final Date created = new Date();

    @Override
    public boolean equals(Object o) {
        return o instanceof EmployeeEntity e
                && Objects.equals(e.getId(), id)
                && Objects.equals(e.getSupervisor(), supervisor)
                && Objects.equals(e.getCreated(), created)
                && Objects.equals(e.getFirstName(), firstName)
                && Objects.equals(e.getLastName(), lastName)
                && Objects.equals(e.getPosition(), position)
                && Objects.equals(e.getSubordinates(), subordinates);
    }
}
