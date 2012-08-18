package org.openmrs.module.hr.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.api.HRTrainingService;
import org.openmrs.module.hr.api.validator.TrainingValidator;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TrainingController {
    private static final String SUCCESS_LIST_VIEW = "/module/hr/admin/trainings";
    private static final String SUCCESS_FORM_VIEW = "/module/hr/admin/training";

    @RequestMapping(value="module/hr/admin/training.form")
    @ModelAttribute("training")
    public HrTraining showForm(@RequestParam(value="trainingId",required=false) Integer trainingId){
        HrTraining hrTraining;
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        if(trainingId != null)
            hrTraining = hrTrainingService.getTrainingById(trainingId);
        else
            hrTraining = new HrTraining();
        return hrTraining;
    }


    @RequestMapping(value = "module/hr/admin/trainings.list")
    public String showList(ModelMap model){
        HRTrainingService hrTrainingService=Context.getService(HRTrainingService.class);
        List<HrTraining> hrTrainingList= hrTrainingService.getTrainings(true);
        model.addAttribute("trainingsList",hrTrainingList);
        return SUCCESS_LIST_VIEW;
    }

    @RequestMapping(value ="module/hr/admin/training.form", method = RequestMethod.POST)
    public ModelAndView createOrUpdateTraining(HttpServletRequest request,@ModelAttribute("training")  HrTraining training, BindingResult errors){
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);

        if(request.getParameter("unretireTraining") != null)
            return unRetireTraining(request, training, hrTrainingService, errors);

        new TrainingValidator().validate(training,errors);
        if(errors.hasErrors())
            return new ModelAndView(SUCCESS_FORM_VIEW);

        if(request.getParameter("retireTraining") != null)
            return checkAndRetireTraining(request, training, hrTrainingService , errors);

        hrTrainingService.saveTraining(training);
        request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Training saved Successfully");
        return new ModelAndView(SUCCESS_LIST_VIEW).addObject("trainingsList",hrTrainingService.getTrainings(true));
    }

    private ModelAndView unRetireTraining(HttpServletRequest request, HrTraining training, HRTrainingService hrTrainingService, BindingResult errors) {
        hrTrainingService.unretireTraining(hrTrainingService.getTrainingById(training.getId()));
        request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Training Un-Retired Successfully");
        return new ModelAndView(SUCCESS_LIST_VIEW).addObject("trainingsList",hrTrainingService.getTrainings(true));
    }

    private ModelAndView checkAndRetireTraining(HttpServletRequest request, HrTraining training, HRTrainingService hrTrainingService, BindingResult errors) {
        String retireReason = request.getParameter("retireReason");
        if (training.getId() != null && (retireReason == null || retireReason.length() == 0)) {
            errors.reject("retireReason", "Retire reason cannot be empty");
            return new ModelAndView(SUCCESS_FORM_VIEW);
        }
        hrTrainingService.retireTraining(hrTrainingService.getTrainingById(training.getId()), retireReason, Context.getAuthenticatedUser());
        request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Training Retired Successfully");
        return new ModelAndView(SUCCESS_LIST_VIEW).addObject("trainingsList",hrTrainingService.getTrainings(true));
    }

}
