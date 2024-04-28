package com.itgnostic.test4sandbox.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespEmployeeModel {
    String id;
    String fistName;
    String lastName;
    String position;
    String supervisorFullName;
    String supervisorId;
    String subsFullNames;
}
