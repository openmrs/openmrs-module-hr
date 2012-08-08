package org.openmrs.module.hr.web.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrTrainPerson;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.HRStaffService;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.hr.api.propertyEditor.HrCertificateEditor;
import org.openmrs.module.hr.api.propertyEditor.HrTrainingClassEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.text.NumberFormat;
import java.util.*;

@Controller
@SessionAttributes("staff")
public class TrainPersonController {

    private static final String SUCCESS_LIST_VIEW = "/module/hr/manager/staffTrainings";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
        binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
        binder.registerCustomEditor(HrTrainingClass.class, new HrTrainingClassEditor());
    }


    private Date getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    @RequestMapping(value = "module/hr/manager/staffTrainings.list")
    public String showList(ModelMap model ,@ModelAttribute("staff") HrStaff staff){
        HRStaffService hrStaffService = Context.getService(HRStaffService.class);
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        model.addAttribute("staffTrainings",hrTrainingService.getTrainingHistoryFor(staff.getId()));
        Set<String> categories = new HashSet<String>();
        for(HrTraining training : hrTrainingService.getTrainings())
            categories.add(training.getCategory());
        model.addAttribute("trainingCategories",categories);
        return SUCCESS_LIST_VIEW;
    }

    @RequestMapping(value= "module/hr/manager/staffPerson.form")
    public void create(@RequestParam("hrTrainingClass") HrTrainingClass hrTrainingClass,@ModelAttribute("staff") HrStaff staff){
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        PersonService personService = Context.getService(PersonService.class);
        HrTrainPerson hrTrainPerson= new HrTrainPerson();
        hrTrainPerson.setHrTrainingClass(hrTrainingClass);
        hrTrainPerson.setReason("blank");
        hrTrainPerson.setFollowUpDate(new Date());
        hrTrainPerson.setCompleted(false);
        hrTrainPerson.setPerson(personService.getPerson(staff.getId()));
        hrTrainingService.saveTrainPerson(hrTrainPerson);
    }
}
