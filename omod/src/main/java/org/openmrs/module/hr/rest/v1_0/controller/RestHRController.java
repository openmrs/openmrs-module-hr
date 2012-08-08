package org.openmrs.module.hr.rest.v1_0.controller;

import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.resource.PersonResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/hr")
public class RestHRController {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@RequestParam("method") String method, ModelMap model) {
        return "";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@RequestParam("method") String method, ModelMap model) {
        return "";
    }
    /*
        Rest API to get person for a given staff.
     */
    @RequestMapping(method = RequestMethod.GET, params = "getPersonForStaffId")
    @ResponseBody
    public SimpleObject getConceptsBySet(HttpServletRequest request,
                                         @RequestParam(value = "getPersonForStaffId", required = true) Integer staffID) throws ResponseException {
        SimpleObject simpleObject = new SimpleObject();
        PersonService personService = Context.getService(PersonService.class);
        simpleObject.put("person", personService.getPerson(staffID).getUuid());
//        simpleObject.put("person", new PersonResource().asRepresentation(personService.getPerson(staffID), Representation.REF));
        return simpleObject;
    }
}
