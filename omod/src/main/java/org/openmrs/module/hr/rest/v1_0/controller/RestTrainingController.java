package org.openmrs.module.hr.rest.v1_0.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.rest.v1_0.resource.HRTrainingClassResource;
import org.openmrs.module.hr.rest.v1_0.resource.HRTrainingResource;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.RestUtil;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/hr/training")
public class RestTrainingController extends BaseCrudController<HRTrainingResource> {
    private static final Log log= LogFactory.getLog(RestTrainingController.class);

    @Override
    public HRTrainingResource getResource()
    {
        log.info("getting Training resource");
        return Context.getService(RestService.class).getResource(HRTrainingResource.class);
    }

    @RequestMapping(method = RequestMethod.GET, params = "category")
    @ResponseBody
    public SimpleObject getTrainingByCategory(HttpServletRequest request,
                                         @RequestParam(value = "category", required = true) String category) throws ResponseException {

        RequestContext context = RestUtil.getRequestContext(request);
        PageableResult result = new HRTrainingResource().getTrainingByCategory(category,context);
        return result.toSimpleObject();
    }


}
