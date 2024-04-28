package com.itgnostic.test4sandbox.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class JsonUtilsTest {
    private final String STR = "hahaha";
    private final boolean BOOL = true;
    private final int INT = 123;
    private final Set SET = Set.of(BOOL, INT, STR);
    private final List LIST = Arrays.asList(STR, BOOL, INT, SET);
    private final Map MAP = Map.of("Key1", STR, "Key2", BOOL, "Key3", INT, "Key4", SET, "Key5", LIST);

    @Test
    public void collectionToJsonTest() {
        JSONArray jsonArray = new JSONArray().put(STR).put(BOOL).put(INT).put(SET);

        assertTrue(jsonArray.similar(JsonUtils.collectionToJson(LIST)));
    }

    @Test
    public void mapToJsonTest() {
        JSONObject jsonObject = new JSONObject(MAP);

        assertTrue(jsonObject.similar(JsonUtils.mapToJson(MAP)));
    }

    /*
    private boolean compareJSONArrays(JSONArray a1, JSONArray a2) {
        if (a1 == null && a2 == null)
            return true;
        else if (Objects.isNull(a1) != Objects.isNull(a2))
            return false;
        else if (a1.similar())



        return true;
    }

     */
}
