package com.itgnostic.test4sandbox.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqEmployeeModel {
    String firstName;
    String lastName;
    String position;
    String supervisor;
}
