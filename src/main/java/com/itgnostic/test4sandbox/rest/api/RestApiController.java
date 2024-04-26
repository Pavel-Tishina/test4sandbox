package com.itgnostic.test4sandbox.rest.api;

import com.itgnostic.test4sandbox.errors.RestApiErrors;
import com.itgnostic.test4sandbox.utils.RestApiUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.h2.util.StringUtils;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/api")
public class RestApiController {
    private List<String> errors = new ArrayList<>();
    int status = 200;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployee(@RequestParam(value = "id") String id) {

        if (id == null) {
            errors.add(RestApiErrors.NO_PARAM.getErrorText().formatted("id"));
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
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public ResponseEntity<String> putEmployee(@RequestParam(value = "id") String id) {
        if (id == null) {
            errors.add(RestApiErrors.NO_PARAM.getErrorText().formatted("id"));
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
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public ResponseEntity<String> delEmployee(@RequestParam(value = "id") String id) {
        if (id == null) {
            errors.add(RestApiErrors.NO_PARAM.getErrorText().formatted("id"));
        }
        else {
            int[] _id = RestApiUtils.getIntParamsAsArray(id, true);

            if (_id.length == 0) {
                errors.add(RestApiErrors.BAD_PARAM.getErrorText().formatted("id", id));
            }
        }

        return errors.isEmpty()
                ? ResponseEntity.ok("del employer: " + id)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());
    }

    @RequestMapping(value = "/employee/list", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployeesList(@RequestParam(value = "ids") String ids) {
        if (ids == null) {
            errors.add(RestApiErrors.NO_PARAM.getErrorText().formatted("id"));
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
    }

    @RequestMapping(value = "/employee/subordinates", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployeeSubordinates(@RequestParam(value = "id") String id) {
        if (id == null) {
            errors.add(RestApiErrors.NO_PARAM.getErrorText().formatted("id"));
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
    }


}
