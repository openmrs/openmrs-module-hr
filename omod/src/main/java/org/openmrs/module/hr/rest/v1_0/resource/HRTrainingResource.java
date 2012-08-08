package org.openmrs.module.hr.rest.v1_0.resource;

import java.util.HashSet;
import java.util.Set;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.*;
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
        return Context.getService(HRTrainingService.class).saveTraining(hrTraining);
    }

    @Override
    public void purge(HrTraining hrTraining, RequestContext requestContext) throws ResponseException {
        Context.getService(HRTrainingService.class).deleteTraining(hrTraining);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        DelegatingResourceDescription d=new DelegatingResourceDescription();
        if(rep instanceof DefaultRepresentation)
        {
            d.addProperty("uuid");
            d.addProperty("trainingId");
            d.addProperty("category");
            d.addProperty("name");
            d.addProperty("description");
            d.addSelfLink();
            d.addLink("full", ".?v="+ RestConstants.REPRESENTATION_FULL);
            return d;
        }
        else if(rep instanceof FullRepresentation)
        {
            d.addProperty("uuid");
            d.addProperty("nationalId");
            d.addProperty("category");
            d.addProperty("sortWeight");
            d.addProperty("name");
            d.addProperty("description");
            d.addProperty("hrTrainingClasses",Representation.FULL);
            d.addProperty("auditInfo", findMethod("getAuditInfo"));
            d.addSelfLink();
            return d;

        }

        return null;
    }

    
    @Override
    protected AlreadyPaged<HrTraining> doSearch(String query, RequestContext context) {
        return new ServiceSearcher<HrTraining>(HRTrainingService.class, "getTrainings", "getCountOfTrainings").search(query,
                context);
    }


    @Override
    protected PageableResult doGetAll(RequestContext context) {
        return  new NeedsPaging<HrTraining>(Context.getService(HRTrainingService.class).getTrainings(), context);
    }

    public AlreadyPaged<HrTraining> getTrainingByCategory(String query, RequestContext context){
        return new ServiceSearcher<HrTraining>(HRTrainingService.class,"getTrainingsByCategory","getCountOfTrainingsByCategory").search(query,context);
    }



    @Override
    public DelegatingResourceDescription getCreatableProperties() {
        DelegatingResourceDescription d = new DelegatingResourceDescription();
        d.addProperty("uuid");
        d.addProperty("nationalId");
        d.addRequiredProperty("category");
        d.addProperty("sortWeight");
        d.addRequiredProperty("name");
        d.addProperty("description");
        d.addProperty("hrTrainingClasses");
        return d;
    }

	public String getDisplayString(HrTraining delegate) {
		return delegate.getName();
	}
	
	@Override
	protected String getNamespacePrefix() {
		return "hr";
	}

}
