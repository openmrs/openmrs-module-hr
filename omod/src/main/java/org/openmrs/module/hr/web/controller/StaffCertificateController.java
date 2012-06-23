package org.openmrs.module.hr.web.controller;


import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrCertificate;
import org.openmrs.module.hr.HrPostHistory;
import org.openmrs.module.hr.HrStaff;
import org.openmrs.module.hr.HrStaffCert;
import org.openmrs.module.hr.api.HRQualificationService;
import org.openmrs.module.hr.api.propertyEditor.HrCertificateEditor;
import org.openmrs.module.hr.api.propertyEditor.HrPostHistoryEditor;
import org.openmrs.module.hr.api.validator.CertificateValidator;
import org.openmrs.module.hr.api.validator.StaffCertificateValidator;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
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
public class StaffCertificateController {

    private static final String SUCCESS_LIST_VIEW = "/module/hr/manager/staffCertificates";
    private static final String SUCCESS_FORM_VIEW = "/module/hr/manager/staffCertificate";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
        binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
        binder.registerCustomEditor(HrCertificate.class, new HrCertificateEditor());
    }

    @RequestMapping(value="module/hr/manager/staffCertificate.form")
    @ModelAttribute("staffCertificate")
    public HrStaffCert showForm(@RequestParam(value="staffCertificateId",required=false) Integer staffCertificateId,ModelMap model){
        HrStaffCert hrStaffCert;
        HRQualificationService hrQualificationService = Context.getService(HRQualificationService.class);
        if(staffCertificateId != null)
            hrStaffCert = hrQualificationService.getStaffCertificateById(staffCertificateId);
        else
            hrStaffCert = new HrStaffCert();
        model.addAttribute("allCertificatesList",hrQualificationService.getCertificates());
        return hrStaffCert;
    }

    @RequestMapping(value = "module/hr/manager/staffCertificates.list")
    public String showList(ModelMap model ,@ModelAttribute("staff") HrStaff staff){
        HRQualificationService hrQualificationService=Context.getService(HRQualificationService.class);
        List<HrStaffCert> hrStaffCertList = hrQualificationService.getCertificatesForStaff(staff);
        model.addAttribute("staffCertificates",hrStaffCertList);
        return SUCCESS_LIST_VIEW;
    }

    @RequestMapping(value ="module/hr/manager/staffCertificate.form", method = RequestMethod.POST)
    public ModelAndView createOrUpdateStaffCertificate(HttpServletRequest request,@ModelAttribute("staffCertificate")  HrStaffCert hrStaffCert, BindingResult errors,@ModelAttribute("staff") HrStaff staff){
        HRQualificationService hrQualificationService = Context.getService(HRQualificationService.class);

        new StaffCertificateValidator().validate(hrStaffCert,errors);
        if(errors.hasErrors())
            return new ModelAndView(SUCCESS_FORM_VIEW).addObject("allCertificatesList",hrQualificationService.getCertificates());

        hrStaffCert.setHrStaff(staff);
        hrQualificationService.saveStaffCertificate(hrStaffCert);
        request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Staff Certificate added Successfully");
        return new ModelAndView(SUCCESS_LIST_VIEW).addObject("staffCertificates",hrQualificationService.getCertificatesForStaff(staff));
    }

}
