package org.openmrs.module.hr.rest.v1_0.resource;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource("trainPerson")
@Handler(supports = HrTrainPerson.class, order = 0)
public class HRTrainPersonResource extends DelegatingCrudResource<HrTrainPerson>{

    @Override
    public HrTrainPerson getByUniqueId(String uuid) {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        return hrTrainingService.getTrainPersonByUniqueID(uuid);
    }

    @Override
    protected void delete(HrTrainPerson hrTrainPerson, String s, RequestContext requestContext) throws ResponseException {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainPerson.setVoidReason(s);
        hrTrainPerson.setVoidedBy(Context.getAuthenticatedUser());
        hrTrainingService.saveTrainPerson(hrTrainPerson);
    }

    @Override
    public HrTrainPerson newDelegate() {
        return new HrTrainPerson();
    }

    @Override
    public HrTrainPerson save(HrTrainPerson hrTrainPerson) {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainingService.saveTrainPerson(hrTrainPerson);
        return hrTrainPerson;
    }

    @Override
    public void purge(HrTrainPerson hrTrainPerson, RequestContext requestContext) throws ResponseException {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainingService.deleteTrainPerson(hrTrainPerson);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
