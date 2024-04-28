package com.itgnostic.test4sandbox.errors;

public enum ValueErrors {
    ID_IS_NULL("ID can't be null"),
    ID_IS_ZERO_OR_MINUS("ID can't be zero or minus"),
    ID_LIST_IS_NULL_OR_EMPTY("IDs list can't be null or empty"),
    EMPLOYEES_ID_IS_NULL("Employee's ID can't be null"),
    NOT_SET_FIRST_NAME("First Name can't be null or empty"),
    NOT_SET_LAST_NAME("Last Name can't be null or empty");

    private String error;

    ValueErrors(String error) {
        this.error = error;
    }

    public String getErrorText() {
        return error;
    }

}
