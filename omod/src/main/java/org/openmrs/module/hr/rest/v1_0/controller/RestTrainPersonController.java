package org.openmrs.module.hr.rest.v1_0.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.rest.v1_0.resource.HRTrainPersonResource;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/hr/trainPerson")
public class RestTrainPersonController extends BaseCrudController<HRTrainPersonResource> {
    private static final Log log= LogFactory.getLog(RestTrainingController.class);

    @Override
    public HRTrainPersonResource getResource()
    {
        log.info("getting TrainPerson resource");
        return Context.getService(RestService.class).getResource(HRTrainPersonResource.class);
    }
}