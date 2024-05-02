package com.itgnostic.test4sandbox.service;

import com.google.common.base.Strings;
import com.itgnostic.test4sandbox.db.entity.EmployeeEntity;
import com.itgnostic.test4sandbox.errors.DbErrors;
import com.itgnostic.test4sandbox.utils.EmployeeUtils;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.itgnostic.test4sandbox.common.Const.DT;
import static com.itgnostic.test4sandbox.errors.DbErrors.BAD_RESULT;
import static com.itgnostic.test4sandbox.errors.DbErrors.DB_ERROR;

public class OperationResult {
    @Getter
    private List<EmployeeEntity> resultList = new ArrayList<>();
    @Getter
    private final List<String> errorList = new ArrayList<>();

    OperationResult() {}

    OperationResult(Object result) {
        if (result instanceof EmployeeEntity r)
            addResult(r);
        else if (result instanceof List rl && !rl.isEmpty() && rl.iterator().next() instanceof EmployeeEntity)
            addResult((List<EmployeeEntity>) rl);
        else if (result == null)
            addError(BAD_RESULT);
    }

    public void addResult(EmployeeEntity result) {
        if (result != null)
            resultList.add(result);
        else
            addError(BAD_RESULT);
    }

    public void addResult(Collection<EmployeeEntity> result) {
        if (result != null)
            result.forEach(this::addResult);
        else
            addError();
    }

    public void addResult(Boolean result) {
        if (result == null || !result)
            addError();
    }

    public void addError() {
        addError(DB_ERROR);
    }
    public void addError(String error) {
        errorList.add(error);
    }

    public void addError(DbErrors error) {
        errorList.add(error.getErrorText());
    }

    public boolean hasErrors() {
        return !errorList.isEmpty();
    }

    public boolean isSuccess() {
        return !resultList.isEmpty() || errorList.isEmpty();
    }

    public String getErrorDetails() {
        return hasErrors()
                ? "Result has " + errorList.size() + " errors:" + String.join("\n\t", errorList)
                : "No errors";
    }

    public List<Map<String, Object>> getResultsAsList() {
        return resultList.stream()
                .filter(EmployeeUtils::allReqFieldsOk)
                .flatMap(e -> {
                    Map<String, Object> map = Map.of(
                            "id", e.getId(),
                            "first name", e.getFirstName(),
                            "last name", e.getLastName(),
                            "position", Strings.nullToEmpty(e.getPosition()),
                            "supervisor", e.getSupervisor() == null ? "" : e.getSupervisor(),
                            "subordinates", e.getSubordinates(),
                            "created date", DT.format(e.getCreated())
                    );
                    return Stream.of(map);
                })
                .collect(Collectors.toList());
    }

}
