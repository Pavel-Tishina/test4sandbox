package com.itgnostic.test4sandbox.db.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static com.itgnostic.test4sandbox.common.Const.DB_TABLE_NAME;

@NoArgsConstructor
@Entity
@Table(
        name = DB_TABLE_NAME,
        indexes = {@Index(name = "const", columnList = "id, created")}
)
public class EmployeeEntity {
    @Getter
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Getter @Setter @Column(name="FIRST_NAME")
    private String firstName;
    @Getter @Setter @Column(name="LAST_NAME")
    private String lastName;
    @Getter @Setter @Column(name="A_POSITION")
    private String position;
    @Getter @Setter @Column(name="SUPERVISOR")
    private Long supervisor;
    @Getter @Setter @Column(name="SUBORDINATES")
    private final Set<Long> subordinates = new HashSet<>();

    @Getter
    @Column(name="CREATED_DATE")
    private final Date created = new Date();

    @Override
    public boolean equals(Object o) {
        return o instanceof EmployeeEntity e
                && ((e.getId() == null && id == null)
                    || (e.getId() != null && e.equals(id)))
                && (e.getCreated().equals(created))
                && ((e.getSupervisor() == null && supervisor == null)
                    || (e.getSupervisor() != null && e.getSupervisor().equals(supervisor)))
                && ((lastName == null && e.getLastName() == null)
                    || (e.getLastName() != null && e.getLastName().equals(lastName)))
                && ((position == null && e.getPosition() == null)
                    || (e.getPosition() != null && e.getPosition().equals(position)))
                && ((firstName == null && e.getFirstName() == null)
                    || (e.getFirstName() != null && e.getFirstName().equals(firstName)))
                && e.getSubordinates().equals(subordinates);
    }
}
