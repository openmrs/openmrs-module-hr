package org.openmrs.module.hr.rest.v1_0.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.rest.v1_0.resource.HRTrainPersonResource;
import org.openmrs.module.hr.rest.v1_0.resource.HRTrainingClassResource;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseSubResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for REST web service access to the TrainingClass. Supports CRUD on the resource itself.
 */
@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/hr/training/{parentUuid}/trainingclass")
public class RestTrainingClassController extends BaseSubResourceController<HRTrainingClassResource> {

    private static final Log log= LogFactory.getLog(RestTrainingClassController.class);

    @Override
    public HRTrainingClassResource getResource()
    {
        log.info("getting Training Class resource");
        return Context.getService(RestService.class).getResource(HRTrainingClassResource.class);
    }

}
