package org.openmrs.module.hr.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrTraining;
import org.openmrs.module.hr.HrTrainingClass;
import org.openmrs.module.hr.api.HRTrainingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TrainingManagementController {

    private static final String SUCCESS_LIST_VIEW = "/module/hr/admin/trainingManagement";
    @RequestMapping(value = "module/hr/admin/trainingManagement.list")
    public String showList(ModelMap model){
        HRTrainingService hrTrainingService= Context.getService(HRTrainingService.class);
        List<HrTraining> hrTrainingList= hrTrainingService.getTrainings();
        model.addAttribute("trainingsList",hrTrainingList);
        return SUCCESS_LIST_VIEW;
    }

    @RequestMapping(value="/module/hr/admin/trainingClasses.json")
    public @ResponseBody
    HrTrainingClass getTrainingClassesJson(@RequestParam(value="trainingClassId",required=true) Integer trainingId){
        HRTrainingService hrTrainingService = Context.getService(HRTrainingService.class);
        HrTraining hrTraining = hrTrainingService.getTrainingById(trainingId);
        System.out.println("THIS____________------------>"+hrTraining.getCategory());
        HrTrainingClass hrTrainingClass =(HrTrainingClass)hrTraining.getHrTrainingClasses().toArray()[0];
        return hrTrainingClass;
    }
}
