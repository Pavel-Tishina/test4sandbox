package com.itgnostic.test4sandbox.utils;

import org.h2.util.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RestApiUtilsTest {
    private static final String PARAMS_BAD_1 = null;
    private static final String PARAMS_BAD_2 = "";
    private static final String PARAMS_BAD_3 = "&&&";
    private static final String PARAMS_BAD_4 = "one ";
    private static final String PARAMS_BAD_5 = "\t5";
    private static final String PARAMS_BAD_6 = "5, one";
    private static final String PARAMS_BAD_7 = "5,one&";
    private static final String PARAMS_OK_1 = "one";
    private static final String PARAMS_OK_2 = "5";
    private static final String PARAMS_OK_3 = "5,one";


    @Test
    public void getParamsAsArrayTest() {
        String[] badValue = new String[0];
        String[] oneValue = {"one"};
        String[] oneValue2 = {"5"};
        String[] lotValues = {"5","one"};

        assertEquals(badValue, RestApiUtils.getParamsAsArray(PARAMS_BAD_1));
        assertEquals(badValue, RestApiUtils.getParamsAsArray(PARAMS_BAD_2));
        assertEquals(badValue, RestApiUtils.getParamsAsArray(PARAMS_BAD_3));
        assertEquals(badValue, RestApiUtils.getParamsAsArray(PARAMS_BAD_4));
        assertEquals(badValue, RestApiUtils.getParamsAsArray(PARAMS_BAD_5));
        assertEquals(badValue, RestApiUtils.getParamsAsArray(PARAMS_BAD_6));
        assertEquals(badValue, RestApiUtils.getParamsAsArray(PARAMS_BAD_7));

        assertEquals(oneValue, RestApiUtils.getParamsAsArray(PARAMS_OK_1));
        assertEquals(oneValue2, RestApiUtils.getParamsAsArray(PARAMS_OK_2));
        assertEquals(lotValues, RestApiUtils.getParamsAsArray(PARAMS_OK_3));
    }
}
