package org.openmrs.module.hr.web.controller;
                 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.hr.api.HRStaffService;
import org.openmrs.module.hr.api.propertyEditor.HrTrainingEditor;
import org.openmrs.module.hr.api.validator.TrainingClassValidator;
import org.openmrs.web.WebConstants;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.List;

@Controller
public class TrainingClassController {
    private Log log = LogFactory.getLog(this.getClass());

    private static final String SUCCESS_LIST_VIEW = "/module/hr/admin/trainingClasses";
    private static final String SUCCESS_FORM_VIEW = "/module/hr/admin/trainingClass";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
        binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, nf, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
        binder.registerCustomEditor(HrTraining.class, new HrTrainingEditor());
    }

    @RequestMapping(value="module/hr/admin/trainingClass.form")
    @ModelAttribute("trainingClass")
    public HrTrainingClass showForm(@RequestParam(value="trainingClassId",required=false) Integer trainingClassId,ModelMap model){
        HrTrainingClass hrTrainingClass;
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        if(trainingClassId != null)
            hrTrainingClass = hrTrainingService.getTrainingClassById(trainingClassId);
        else
            hrTrainingClass = new HrTrainingClass();
        model.addAttribute("allTrainingsList", hrTrainingService.getTrainings(false));
        return hrTrainingClass;
    }


    @RequestMapping(value = "module/hr/admin/trainingClasses.list")
    public String showList(ModelMap model){
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        List<HrTrainingClass> hrTrainingClassList =hrTrainingService.getTrainingClasses();
        model.addAttribute("trainingClassesList",hrTrainingClassList);
        return SUCCESS_LIST_VIEW;
    }

    @RequestMapping(value ="module/hr/admin/trainingClass.form", method = RequestMethod.POST)
    public ModelAndView createOrUpdateTrainingClass(HttpServletRequest request,@ModelAttribute("trainingClass")  HrTrainingClass hrTrainingClass, BindingResult errors){
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        HRStaffService hrStaffService = Context.getService(HRStaffService.class);
        if(request.getParameter("action").equalsIgnoreCase(Context.getMessageSourceService().getMessage("hr.action.training.classes.delete")))
            deleteTrainingClass(request,hrTrainingService,hrTrainingClass);
        else{
            new TrainingClassValidator().validate(hrTrainingClass,errors);
            if(errors.hasErrors())
                return new ModelAndView(SUCCESS_FORM_VIEW).addObject("allTrainingsList", hrTrainingService.getTrainings(false));


            hrTrainingService.saveTrainingClass(hrTrainingClass);
            request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Training Class Saved Successfully");
        }
        return new ModelAndView(SUCCESS_LIST_VIEW).addObject("trainingClassesList",hrTrainingService.getTrainingClasses());
    }

    private void deleteTrainingClass(HttpServletRequest request, HRTrainingService hrTrainingService, HrTrainingClass hrTrainingClass) {
        hrTrainingService.deleteTrainingClasses(hrTrainingService.getTrainingClassById(hrTrainingClass.getTrainingClassId()));
        request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Training Class Deleted Successfully");
    }

}
