package org.openmrs.module.hr.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.ModuleException;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.util.DatabaseUpdater;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "module/hr/admin/setup")
public class AdminSetupController {


    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());

    /** Success form view name */
    private String targetView = null;

    @RequestMapping(method = RequestMethod.GET)
    public void showForm(HttpServletRequest request, ModelMap model,
                         @RequestParam(required = false, defaultValue = "admin/index") String targetView) {
        this.targetView = targetView;

        GlobalProperty centric = Context.getAdministrationService().getGlobalPropertyObject("hr.Centric");
        GlobalProperty staffAttributeToDisplay = Context.getAdministrationService().getGlobalPropertyObject("hr.Staff_Attribute_to_display");
        List<GlobalProperty> globalProperties = new ArrayList<GlobalProperty>();
        globalProperties.add(centric);
        globalProperties.add(staffAttributeToDisplay);

        model.put("globalPropertiesObject", globalProperties);

        String sampleDataInstalled = Context.getAdministrationService().getGlobalProperty("hr.setup.sampleData");
        boolean isSampleDataInstalled = sampleDataInstalled != null && !sampleDataInstalled.isEmpty();

        model.put("sampleDataInstalled", isSampleDataInstalled);
    }

    @RequestMapping(value = "/installSampleData", method = RequestMethod.GET)
    @ResponseBody
    public SimpleObject installSampleData() {
        SimpleObject o = new SimpleObject();

        try {
            DatabaseUpdater.executeChangelog("liquibase-sample-data.xml", null, null, null, ModuleFactory.getModuleClassLoader(ModuleFactory.getModuleById("hr")));
            markSampleDataInstalled();
            o.put("code", "success");
            o.put("message", Context.getMessageSourceService().getMessage("hr.setup.database.sampledata.result.success"));
            return o;
        } catch (ModuleException e) {
            log.error("Failed to retrieve class loader for hr", e);
            o.put("code", "failure");
            o.put("message", Context.getMessageSourceService().getMessage("hr.setup.database.sampledata.result.failed") + ": " + e.toString() );
        } catch (Exception e) {
            log.error("Applying sample data using liquibase failed", e);
            o.put("code", "failure");
            o.put("message", Context.getMessageSourceService().getMessage("hr.setup.database.sampledata.result.failed"));
        }

        return o;
    }


    @RequestMapping(value = "/finishSetup", method = RequestMethod.GET)
    @ResponseBody
    public String finishSetup(HttpServletRequest request) {
        markSetupCompleted();
        return request.getContextPath() + "/" + targetView + ".htm";
    }

    private void markSampleDataInstalled() {
        Context.getAdministrationService().saveGlobalProperty(new GlobalProperty("hr.setup.sampleData", "installed"));
    }
    private void markSetupCompleted() {
        Context.getAdministrationService().saveGlobalProperty(new GlobalProperty("hr.setup", "complete"));
    }

}
