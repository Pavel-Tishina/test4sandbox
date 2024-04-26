package com.itgnostic.test4sandbox.errors;

public enum DbErrors {
    EMPLOYEE_NOT_FOUND("Not found Employee by id '%d'"),
    EMPLOYEE_ALREADY_EXIST("Employee with id '%d' already exist"),
    EMPLOYEE_ALREADY_HAS_SUPERVISOR("Employee '%s' already has a Supervisor"),
    EMPLOYEE_CAN_NOT_BE_SUPERVISOR("Employee '%s' can not be a Supervisor for '%s'"),
    SUPERVISOR_NOT_FOUND("Not found Supervisor by id '%d'"),
    SUPERVISOR_SUBORDINATES_NOT_FOUND("Not found Subordinate by id '%d' for Supervisor '%s'");

    private String error;

    DbErrors(String error) {
        this.error = error;
    }

    public String getErrorText() {
        return error;
    }

}
