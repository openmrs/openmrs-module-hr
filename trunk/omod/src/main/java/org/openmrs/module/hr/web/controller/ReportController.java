package org.openmrs.module.hr.web.controller;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrReport;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {

	private final String SUCCESS_LIST_VIEW = "/module/hr/admin/reportSelection";
	private final String SUCCESS_FORM_VIEW = "/module/hr/admin/generateReport";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		NumberFormat nf = NumberFormat.getInstance(Context.getLocale());
		binder.registerCustomEditor(java.lang.Integer.class, new CustomNumberEditor(java.lang.Integer.class, nf, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(Context.getDateFormat(), true, 10));
		binder.registerCustomEditor(org.openmrs.Concept.class, new ConceptEditor());
		binder.registerCustomEditor(Location.class, new LocationEditor());
		binder.registerCustomEditor(java.lang.Boolean.class, new CustomBooleanEditor(false));
	}
	
	@RequestMapping(value = "module/hr/admin/reportSelection.list",method=RequestMethod.GET)
	public String showList(ModelMap model){
		HRService hrService=Context.getService(HRService.class);
		model.addAttribute("ReportList", hrService.getHrReports());
		return SUCCESS_LIST_VIEW;
	}
	
	@RequestMapping(value = "module/hr/admin/generateReport.form",method=RequestMethod.GET)
	@ModelAttribute("HrReport")
	public HrReport showForm(ModelMap model,@RequestParam(required=false,value="reportId") Integer reportId){
		HRService hrService=Context.getService(HRService.class);
		if(reportId!=null)
		return hrService.getHrReport(reportId);
		else
			return new HrReport();
	}
	@RequestMapping(value = "module/hr/admin/generateReport.form",method=RequestMethod.POST)
	public String onSubmit(HttpServletRequest request,@ModelAttribute("HrReport") HrReport report,BindingResult errors)
	{
		
		//System.out.println(request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath()));
		return SUCCESS_FORM_VIEW;
	}
}
