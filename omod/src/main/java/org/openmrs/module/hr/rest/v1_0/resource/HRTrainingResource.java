package org.openmrs.module.hr.rest.v1_0.resource;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource("training")
@Handler(supports = HrTraining.class, order = 0)
public class HRTrainingResource extends MetadataDelegatingCrudResource<HrTraining> {
    @Override
    public HrTraining getByUniqueId(String uuid) {
        return Context.getService(HRTrainingService.class).getTrainingByUniqueId(uuid);
    }

    @Override
    public void delete(HrTraining hrTraining, String s, RequestContext requestContext) throws ResponseException {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainingService.retireTraining(hrTraining,s,Context.getAuthenticatedUser());
    }

    @Override
    public HrTraining newDelegate() {
        return new HrTraining();
    }

    @Override
    public HrTraining save(HrTraining hrTraining) {
        Context.getService(HRTrainingService.class).saveTraining(hrTraining);
        return hrTraining;
    }

    @Override
    public void purge(HrTraining hrTraining, RequestContext requestContext) throws ResponseException {
        Context.getService(HRTrainingService.class).deleteTraining(hrTraining);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription Descri=new DelegatingResourceDescription();
        if(rep instanceof DefaultRepresentation)
        {
            Descri.addProperty("uuid");
            Descri.addProperty("trainingId");
            Descri.addProperty("category");
            Descri.addSelfLink();
            Descri.addLink("full", ".?v="+ RestConstants.REPRESENTATION_FULL);
            return Descri;
        }
        else if(rep instanceof FullRepresentation)
        {
            Descri.addProperty("uuid");
            Descri.addProperty("trainingId");
            Descri.addProperty("category");
            Descri.addProperty("hrTrainingClasses");
            return Descri;

        }
        return null;
    }
}
