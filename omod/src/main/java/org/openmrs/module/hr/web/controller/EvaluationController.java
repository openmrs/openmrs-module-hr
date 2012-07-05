package org.openmrs.module.hr.web.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.*;
import org.openmrs.module.hr.api.HRCompetencyService;
import org.openmrs.module.hr.api.propertyEditor.HrCompetencyEditor;
import org.openmrs.module.hr.api.validator.EvaluationValidator;
import org.openmrs.propertyeditor.PersonEditor;
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
@SessionAttributes("staff")
public class EvaluationController {

    private Log log = LogFactory.getLog(this.getClass());

    private static final String SUCCESS_LIST_VIEW = "/module/hr/manager/evaluations";
    private static final String SUCCESS_FORM_VIEW = "/module/hr/manager/evaluation";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
        binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
        binder.registerCustomEditor(HrCompetency.class, new HrCompetencyEditor());
        binder.registerCustomEditor(Person.class,new PersonEditor());
    }

    @RequestMapping(value="module/hr/manager/evaluation.form")
    @ModelAttribute("evaluation")
    public HrEvaluation showForm(@RequestParam(value="evaluationId",required=false) Integer evaluationId,ModelMap model){
        HrEvaluation hrEvaluation;
        HRCompetencyService hrCompetencyService = Context.getService(HRCompetencyService.class);
        if(evaluationId != null)
            hrEvaluation = hrCompetencyService.getEvaluationById(evaluationId);
        else
            hrEvaluation = new HrEvaluation();
        model.addAttribute("allCompetenciesList", hrCompetencyService.getCompetencies());
        return hrEvaluation;
    }


    @RequestMapping(value = "module/hr/manager/evaluations.list")
    public String showList(ModelMap model ,@ModelAttribute("staff") HrStaff staff){
        HRCompetencyService hrCompetencyService = Context.getService(HRCompetencyService.class);
        List<HrEvaluation> hrEvaluationList = hrCompetencyService.getEvaluationsForStaff(staff);
        model.addAttribute("evaluations",hrEvaluationList);
        return SUCCESS_LIST_VIEW;
    }

    @RequestMapping(value ="module/hr/manager/evaluation.form", method = RequestMethod.POST)
    public ModelAndView createOrUpdateEvaluation(HttpServletRequest request,@ModelAttribute("evaluation")  HrEvaluation hrEvaluation, BindingResult errors,@ModelAttribute("staff") HrStaff staff){
        HRCompetencyService hrCompetencyService = Context.getService(HRCompetencyService.class);
        if(request.getParameter("action").equalsIgnoreCase("Delete Staff Evaluation"))
            deleteEvaluation(request,hrCompetencyService,hrEvaluation);
        else{
            new EvaluationValidator().validate(hrEvaluation,errors);
            if(errors.hasErrors())
                return new ModelAndView(SUCCESS_FORM_VIEW).addObject("allCompetenciesList", hrCompetencyService.getCompetencies());


            hrEvaluation.setHrStaff(staff);
            hrCompetencyService.saveEvaluation(hrEvaluation);
            request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Evaluation Saved Successfully");
        }
        return new ModelAndView(SUCCESS_LIST_VIEW).addObject("evaluations", hrCompetencyService.getEvaluationsForStaff(staff));
    }

    private void deleteEvaluation(HttpServletRequest request, HRCompetencyService hrCompetencyService, HrEvaluation hrEvaluation) {
        hrCompetencyService.deleteEvaluation(hrCompetencyService.getEvaluationById(hrEvaluation.getEvaluationId()));
        request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Evaluation Deleted Successfully");
    }


}
