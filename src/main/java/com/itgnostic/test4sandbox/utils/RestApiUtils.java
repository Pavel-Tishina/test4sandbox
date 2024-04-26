package com.itgnostic.test4sandbox.utils;

import org.h2.util.StringUtils;

public class RestApiUtils {
    private static final String REGEX_PARAMS = "(\\w,?)+";
    public static String[] getParamsAsArray(String paramValue) {
        return !StringUtils.isNullOrEmpty(paramValue) && paramValue.matches(REGEX_PARAMS)
                ? paramValue.split(",")
                : new String[0];
    }
}
