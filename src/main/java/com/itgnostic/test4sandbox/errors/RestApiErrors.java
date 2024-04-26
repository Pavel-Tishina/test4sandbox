package com.itgnostic.test4sandbox.errors;

public enum RestApiErrors {
    BAD_PARAM("Param '%s' has bad value '%s'"),
    NO_PARAM("Not found param '%s'"),
    NO_PARAM_VALUE("There is no value of param %s"),
    RECORD_NOT_FOUND("Not found Employee by id '%s'");

    private String error;

    RestApiErrors(String error) {
        this.error = error;
    }

    public String getErrorText() {
        return error;
    }
}
