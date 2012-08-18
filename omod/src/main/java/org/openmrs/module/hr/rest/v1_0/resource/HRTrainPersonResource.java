package org.openmrs.module.hr.rest.v1_0.resource;

import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.resource.PersonResource;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

@Resource("trainPerson")
@Handler(supports = HrTrainPerson.class, order = 0)
public class HRTrainPersonResource extends DataDelegatingCrudResource<HrTrainPerson> {

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
        hrTrainPerson.setFollowUpDate(new Date());
        if(hrTrainPerson.getCompleted() == null || !hrTrainPerson.getCompleted())
            hrTrainPerson.setCompleted(false);
        hrTrainingService.saveTrainPerson(hrTrainPerson);
        return hrTrainPerson;
    }

    @Override
    public void purge(HrTrainPerson hrTrainPerson, RequestContext requestContext) throws ResponseException {
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        hrTrainingService.deleteTrainPerson(hrTrainPerson);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription Descri=new DelegatingResourceDescription();
        if(rep instanceof DefaultRepresentation)
        {
            Descri.addProperty("uuid");
            Descri.addProperty("hrTrainingClass");
            Descri.addProperty("completed");
            Descri.addProperty("dateCreated");
            Descri.addSelfLink();
            Descri.addLink("full", ".?v="+ RestConstants.REPRESENTATION_FULL);
            return Descri;
        }
        else if(rep instanceof FullRepresentation)
        {
            Descri.addProperty("uuid");
            Descri.addProperty("hrTrainingClass");
            Descri.addProperty("reason");
            Descri.addProperty("followUpDate");
            Descri.addProperty("completed");
            Descri.addProperty("dateCreated");
            Descri.addProperty("hrTrainingClass",Representation.FULL);
            return Descri;

        }

        return null;
    }

    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription d = new DelegatingResourceDescription();
        d.addRequiredProperty("hrTrainingClass");
        d.addRequiredProperty("person");
        d.addProperty("followUpDate");
        d.addProperty("completed");
        d.addProperty("reason");
        return d;
    }

    @PropertySetter("person")
    public static void setPerson(HrTrainPerson instance, String personUUID) {
        PersonResource personResource = new PersonResource();
        instance.setPerson(personResource.getByUniqueId(personUUID));
    }

}
