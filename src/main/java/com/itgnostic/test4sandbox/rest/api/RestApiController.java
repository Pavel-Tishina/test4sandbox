package com.itgnostic.test4sandbox.rest.api;

import com.itgnostic.test4sandbox.utils.RestApiUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api")
public class RestApiController {

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getEmployee(@RequestParam(value = "id") String id) {
        return "get employer: " + id;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String putEmployee(@RequestParam(value = "id") String id) {
        return "put employer: " + id;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public String delEmployee(@RequestParam(value = "id") String id) {
        return "del employer: " + id;
    }

    @RequestMapping(value = "/employees/list", method = RequestMethod.GET)
    public String getEmployeesList(@RequestParam(value = "ids") String ids) {
        return "get employer: " + String.join(",", RestApiUtils.getParamsAsArray(ids));
    }


}
