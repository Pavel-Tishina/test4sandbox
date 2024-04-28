package com.itgnostic.test4sandbox.rest.api;

import com.itgnostic.test4sandbox.errors.RestApiErrors;
import com.itgnostic.test4sandbox.rest.api.model.ReqEmployeeModel;
import com.itgnostic.test4sandbox.service.EmployeeService;
import com.itgnostic.test4sandbox.service.OperationResult;
import com.itgnostic.test4sandbox.utils.JsonUtils;
import com.itgnostic.test4sandbox.utils.RestApiUtils;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.itgnostic.test4sandbox.errors.RestApiErrors.BAD_PARAM;
import static com.itgnostic.test4sandbox.errors.RestApiErrors.NO_PARAM;

@RestController
@RequestMapping(value = "/rest/api")
public class RestApiController {
    private List<String> errors = new ArrayList<>();
    private int status = 200;

    @Autowired
    private EmployeeService employeeService;


    // TODO
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployee(@RequestParam(value = "id") String id) {
        /*
        errors = new ArrayList<>();

        if (id == null) {
            errors.add(NO_PARAM.getErrorText().formatted("id"));
        }
        else {
            int[] _id = RestApiUtils.getIntParamsAsArray(id, true);

            if (_id.length == 0) {
                errors.add(RestApiErrors.BAD_PARAM.getErrorText().formatted("id", id));
            }
        }

        return errors.isEmpty()
                ? ResponseEntity.ok("get employer: " + id)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());

         */
        return null;
    }

    // TODO
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public ResponseEntity<String> putEmployee(@RequestParam(value = "id") String id) {
        /*
        errors = new ArrayList<>();

        if (id == null) {
            errors.add(NO_PARAM.getErrorText().formatted("id"));
        }
        else {
            int[] _id = RestApiUtils.getIntParamsAsArray(id, true);

            if (_id.length == 0) {
                errors.add(RestApiErrors.BAD_PARAM.getErrorText().formatted("id", id));
            }
        }

        return errors.isEmpty()
                ? ResponseEntity.ok("put employer: " + id)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());

         */
        return null;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<String> postEmployee(@RequestBody ReqEmployeeModel newEmployee) {
        errors = new ArrayList<>();

        if (Strings.isBlank(newEmployee.getFirstName()))
            errors.add(RestApiErrors.NO_PARAM_VALUE.getErrorText().formatted("firstName"));
        if (Strings.isBlank(newEmployee.getLastName()))
            errors.add(RestApiErrors.NO_PARAM_VALUE.getErrorText().formatted("lastName"));

        if (!errors.isEmpty())
            return badResponse(HttpStatus.PRECONDITION_FAILED);

        Long supervisor = Objects.isNull(newEmployee.getSupervisor()) || !newEmployee.getSupervisor().matches("\\d+")
                ? null : Long.parseLong(newEmployee.getSupervisor());

        OperationResult result = employeeService.add(
                newEmployee.getFirstName(), newEmployee.getLastName(),
                newEmployee.getPosition(),  supervisor);

        if (result.hasErrors())
            errors.add(result.getErrorDetails());

        return errors.isEmpty() && !result.getResultList().isEmpty()
                ? ResponseEntity.ok(new JSONObject().put("id", result.getResultList().get(0).getId()).toString())
                : badResponse(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public ResponseEntity<String> delEmployee(@RequestParam(value = "id") String id) {
        errors = new ArrayList<>();


        if (id == null)
            errors.add(NO_PARAM.getErrorText().formatted("id"));
        else if (!id.matches("\\d+"))
            errors.add(BAD_PARAM.getErrorText().formatted("id", id));

        if (!errors.isEmpty())
            return badResponse(HttpStatus.PRECONDITION_FAILED);

        long[] _id = RestApiUtils.getIntParamsAsArray(id, true);

        OperationResult result = employeeService.del(_id[0]);

        return result.isSuccess()
                ? ResponseEntity.ok(new JSONObject().put("result", "User with id '%d' was deleted".formatted(_id[0])).toString())
                : badResponse(HttpStatus.INTERNAL_SERVER_ERROR, result);
    }

    // TODO
    @RequestMapping(value = "/employee/list", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployeesList(@RequestParam(value = "ids") String ids) {
        /*
        errors = new ArrayList<>();

        if (ids == null) {
            errors.add(NO_PARAM.getErrorText().formatted("id"));
        }
        else {
            int[] _id = RestApiUtils.getIntParamsAsArray(ids, true);

            if (_id.length == 0) {
                errors.add(RestApiErrors.BAD_PARAM.getErrorText().formatted("ids", ids));
            }
        }

        return errors.isEmpty()
                ? ResponseEntity.ok("get list: " + ids)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());

         */

        return null;
    }

    @RequestMapping(value = "/employee/page", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployeesPage(@RequestParam(value = "p") String p, @RequestParam(value = "lim") String lim) {
        errors = new ArrayList<>();
        Long page = null;
        Long limit = null;

        if (Strings.isBlank(p))
            errors.add(NO_PARAM.getErrorText().formatted("p"));
        else if (!p.matches("\\d+"))
            errors.add(RestApiErrors.BAD_PARAM.getErrorText().formatted("p", p));
        else
            page = Long.parseLong(p);

        if (Strings.isBlank(lim))
            errors.add(NO_PARAM.getErrorText().formatted("lim"));
        else if (!lim.matches("\\d+"))
            errors.add(RestApiErrors.BAD_PARAM.getErrorText().formatted("lim", lim));
        else
            limit = Long.parseLong(lim);

        if (page == null || lim == null)
            return badResponse(HttpStatus.PRECONDITION_FAILED);

        OperationResult result = employeeService.get(page, limit);
        if (result.hasErrors())
            errors.add(result.getErrorDetails());

        return result.isSuccess()
                ? okResponse(result)
                : badResponse(HttpStatus.NOT_FOUND, result);
    }

    // TODO
    @RequestMapping(value = "/employee/subordinates", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployeeSubordinates(@RequestParam(value = "id") String id) {
        /*
        errors = new ArrayList<>();

        if (id == null) {
            errors.add(NO_PARAM.getErrorText().formatted("id"));
        }
        else {
            int[] _id = RestApiUtils.getIntParamsAsArray(id, true);

            if (_id.length == 0) {
                errors.add(RestApiErrors.BAD_PARAM.getErrorText().formatted("id", id));
            }
        }

        return errors.isEmpty()
                ? ResponseEntity.ok("get subordinates: " + id)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());

         */

        return null;
    }

    private ResponseEntity<String> badResponse(HttpStatus status) {
        return ResponseEntity.status(status).body(new JSONObject().put("errors", new JSONArray(errors)).toString());
    }

    private ResponseEntity<String> badResponse(HttpStatus status, OperationResult result) {
        return ResponseEntity.status(status).body(JsonUtils.operationResultToJson(result, errors).toString());
    }

    private ResponseEntity<String> okResponse(OperationResult result) {
        return ResponseEntity.ok().body(JsonUtils.operationResultToJson(result, errors).toString());
    }

}
