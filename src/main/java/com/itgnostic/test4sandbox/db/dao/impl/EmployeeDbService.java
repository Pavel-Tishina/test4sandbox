package com.itgnostic.test4sandbox.db.dao.impl;

import com.itgnostic.test4sandbox.db.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDbService {
    EmployeeEntity add(EmployeeEntity e);
    EmployeeEntity get(int id);
    List<EmployeeEntity> getList(int[] ids);
    List<EmployeeEntity> getList(List<Integer> ids);

    EmployeeEntity modify(EmployeeEntity e);

    Boolean del(EmployeeEntity e);
    Boolean del(int id);

}

