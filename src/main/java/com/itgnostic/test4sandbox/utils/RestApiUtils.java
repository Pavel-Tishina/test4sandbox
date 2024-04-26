package com.itgnostic.test4sandbox.utils;

import org.h2.util.StringUtils;

import java.util.stream.Stream;

public class RestApiUtils {
    private static final String REGEX_PARAMS = "(\\w+,?)+";
    private static final String REGEX_INT_PARAMS = "(\\d+,?)+";
    public static String[] getParamsAsArray(String paramValue, boolean onlyUnique) {
        return !StringUtils.isNullOrEmpty(paramValue) && paramValue.matches(REGEX_PARAMS)
                ? split2String(paramValue, onlyUnique)
                : new String[0];
    }

    public static int[] getIntParamsAsArray(String paramValue, boolean onlyUnique) {
        return !StringUtils.isNullOrEmpty(paramValue) && paramValue.matches(REGEX_INT_PARAMS)
                ? split2Int(paramValue, onlyUnique)
                : new int[0];
    }

    private static String[] split2String(String paramValue, boolean onlyUnique) {
        return onlyUnique
                ? Stream.of(paramValue.split(",")).distinct().toArray(String[]::new)
                : paramValue.split(",");
    }

    private static int[] split2Int(String paramValue, boolean onlyUnique) {
        return onlyUnique
                ? Stream.of(paramValue.split(",")).mapToInt(Integer::parseInt).distinct().toArray()
                : Stream.of(paramValue.split(",")).mapToInt(Integer::parseInt).toArray();
    }
}
