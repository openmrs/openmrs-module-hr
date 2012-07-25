package org.openmrs.module.hr.rest.v1_0.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.rest.v1_0.resource.HRTrainingClassResource;
import org.openmrs.module.hr.rest.v1_0.resource.HRTrainingResource;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/hr/training")
public class RestTrainingController extends BaseCrudController<HRTrainingResource> {
    private static final Log log= LogFactory.getLog(RestTrainingController.class);

    @Override
    public HRTrainingResource getResource()
    {
        log.info("getting TrainingClass resource");
        return Context.getService(RestService.class).getResource(HRTrainingResource.class);
    }
}
