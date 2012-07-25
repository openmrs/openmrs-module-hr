package org.openmrs.module.hr.rest.v1_0.resource;

import org.openmrs.Order;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.hr.web.controller.TrainingClassController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.annotation.SubResource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingSubResource;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.resource.OrderResource;

import java.util.ArrayList;
import java.util.List;

@SubResource(parent = HRTrainingResource.class ,path ="trainingClass")
@Handler(supports = HrTrainingClass.class,order = 0)
public class HRTrainingClassResource extends DelegatingSubResource<HrTrainingClass,HrTraining,HRTrainingResource> {
    @Override
    public HrTrainingClass getByUniqueId(String uuid) {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        return hrTrainingService.getTrainingClassByUniqueId(uuid);
    }

    @Override
    protected void delete(HrTrainingClass hrTrainingClass, String s, RequestContext requestContext) throws ResponseException {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainingClass.setVoided(true);
        hrTrainingClass.setVoidReason(s);
        hrTrainingClass.setVoidedBy(Context.getAuthenticatedUser());
        hrTrainingService.saveTrainingClass(hrTrainingClass);
    }

    @Override
    public HrTrainingClass newDelegate() {
        return new HrTrainingClass();
    }

    @Override
    public HrTrainingClass save(HrTrainingClass hrTrainingClass) {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainingService.saveTrainingClass(hrTrainingClass);
        return hrTrainingClass;
    }

    @Override
    public void purge(HrTrainingClass hrTrainingClass, RequestContext requestContext) throws ResponseException {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainingService.deleteTrainingClasses(hrTrainingClass);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription Descri=new DelegatingResourceDescription();
        if(rep instanceof DefaultRepresentation)
        {
            Descri.addProperty("uuid");
            Descri.addProperty("startDate",Representation.REF);
            Descri.addProperty("location");
            Descri.addProperty("organization");
            Descri.addSelfLink();
            Descri.addLink("full", ".?v="+ RestConstants.REPRESENTATION_FULL);
            return Descri;
        }
        else if(rep instanceof FullRepresentation)
        {
            //
            Descri.addProperty("uuid");
            Descri.addProperty("startDate",Representation.REF);
            Descri.addProperty("location");
            Descri.addProperty("organization");
            Descri.addProperty("duration");
            Descri.addProperty("ceunits");
            Descri.addProperty("instructor");
            Descri.addProperty("fundingSource");
            Descri.addProperty("costCourse");
            Descri.addProperty("costRegister");
            Descri.addProperty("costTravel");
            Descri.addProperty("costPerdiem");
            return Descri;

        }
        return null;
    }


    @Override
    public HrTraining getParent(HrTrainingClass hrTrainingClass) {
        return hrTrainingClass.getHrTraining();
    }

    @Override
    public void setParent(HrTrainingClass hrTrainingClass, HrTraining hrTraining) {
        hrTrainingClass.setHrTraining(hrTraining);
    }

    @Override
    public PageableResult doGetAll(HrTraining hrTraining, RequestContext requestContext) throws ResponseException {
        List<HrTrainingClass> trainingClasses = new ArrayList<HrTrainingClass>();
        HrTraining parentHrTraining = (HrTraining) hrTraining;
        if (hrTraining != null) {
            trainingClasses.addAll(parentHrTraining.getHrTrainingClasses());
        }
        return new NeedsPaging<HrTrainingClass>(trainingClasses, requestContext);
    }

}
