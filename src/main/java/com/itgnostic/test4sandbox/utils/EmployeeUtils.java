package com.itgnostic.test4sandbox.utils;

import com.itgnostic.test4sandbox.db.entity.EmployeeEntity;

import static com.itgnostic.test4sandbox.common.Const.DT;

public class EmployeeUtils {

    public static String getCreatedDate(EmployeeEntity e) {
        return DT.format(e.getCreated());
    }

    //TODO getting map for UI-elements context

}
