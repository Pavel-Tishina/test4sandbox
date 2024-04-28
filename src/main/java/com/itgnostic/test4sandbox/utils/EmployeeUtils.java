package com.itgnostic.test4sandbox.utils;

import com.itgnostic.test4sandbox.db.entity.EmployeeEntity;

import java.util.Objects;
import java.util.Set;

import static com.itgnostic.test4sandbox.common.Const.DT;

public class EmployeeUtils {

    public static String getCreatedDate(EmployeeEntity e) {
        return DT.format(e.getCreated());
    }

    public static boolean someChanges(EmployeeEntity e,
                                      String newFirstName,
                                      String newLastName,
                                      String newPosition,
                                      Long newSupervisor,
                                      Set<Long> newSubs) {

        return !Objects.equals(e.getSupervisor(), newSupervisor)
                || !Objects.equals(e.getFirstName(), newFirstName)
                || !Objects.equals(e.getLastName(), newLastName)
                || !Objects.equals(e.getPosition(), newPosition)
                || !Objects.equals(e.getSubordinates(), newSubs);
    }

    public static EmployeeEntity updateValues(EmployeeEntity e,
                                      String newFirstName,
                                      String newLastName,
                                      String newPosition,
                                      Long newSupervisor,
                                      Set<Long> newSubs) {
        e.setFirstName(newFirstName);
        e.setLastName(newLastName);
        e.setPosition(newPosition);
        e.setSupervisor(newSupervisor);
        e.setSubordinates(newSubs);
        return e;
    }

    public static String getFullName(EmployeeEntity e) {
        return "%s %s (%d)".formatted(e.getFirstName(), e.getLastName(), e.getId());
    }

    //TODO getting map for UI-elements context

}
