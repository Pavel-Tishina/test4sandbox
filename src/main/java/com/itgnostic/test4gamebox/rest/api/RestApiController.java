package com.itgnostic.test4gamebox.rest.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/rest/api")
public class RestApiController {

    @RequestMapping(value = "/employer", method = RequestMethod.GET)
    public String getEmployer(@RequestParam(value = "id") String id) {
        return "get employer: " + id;
    }

    @RequestMapping(value = "/employer", method = RequestMethod.PUT)
    public String putEmployer(@RequestParam(value = "id") String id) {
        return "put employer: " + id;
    }

    @RequestMapping(value = "/employer", method = RequestMethod.DELETE)
    public String delEmployer(@RequestParam(value = "id") String id) {
        return "del employer: " + id;
    }


}
