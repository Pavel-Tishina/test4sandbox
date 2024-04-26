package com.itgnostic.test4sandbox.utils;

import com.itgnostic.test4sandbox.db.entity.EmployeeEntity;
import org.junit.Test;

import java.util.Date;

import static com.itgnostic.test4sandbox.common.Const.DT;
import static org.junit.Assert.assertEquals;

public class EmployeeUtilsTest {

    @Test
    public void getCreatedDate() {
        EmployeeEntity e = new EmployeeEntity();
        Date now = new Date();

        // Actually may be not... but time is soooo clooose ^^
        assertEquals(now.getTime() == e.getCreated().getTime(),
                DT.format(now).equals(EmployeeUtils.getCreatedDate(e)));
    }

}
