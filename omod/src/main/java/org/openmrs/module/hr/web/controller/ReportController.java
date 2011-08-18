package org.openmrs.module.hr.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrReport;
import org.openmrs.module.hr.HrReportParameter;
import org.openmrs.module.hr.ReportGenerator;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
import org.openmrs.util.OpenmrsUtil;
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

	private static Log log = LogFactory.getLog(ReportController.class);
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
	public String onSubmit(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("HrReport") HrReport report,BindingResult errors) throws IOException
	{
		HRService hrService=Context.getService(HRService.class);
		String outputFormat=request.getParameter("outputFormat");
		HrReport hrReport=hrService.getHrReport(report.getReportId());
		int i=0;
		List<HrReportParameter> parameterList=report.getParameters();
		for(HrReportParameter p:parameterList)
		{
			if(p!=null)
				if(parameterList.get(i)!=null && p.getValue()!=null){
					p.setValueClass(hrReport.getParameters().get(i).getValueClass());
					p.setMappedClass(hrReport.getParameters().get(i).getValueClass());
					p.setName(hrReport.getParameters().get(i).getName());
				}
			i++;
			
		}
		hrReport.setParameters(parameterList);
		String jrxmlUrl=request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())+"/moduleResources/hr/jrxmls/"+hrReport.getFileName();
		File generatedReport=ReportGenerator.generate(hrReport, outputFormat, jrxmlUrl);
		FileInputStream fis=new FileInputStream(generatedReport);
        response.addHeader("Content-Disposition","attachment; filename="+hrReport.getName() );
        if(outputFormat.equals("PDF"))
        response.setContentType("application/pdf");
        else if(outputFormat.equals("Excel"))
        response.setContentType("application/vnd.ms-excel");
        response.setContentLength( (int) generatedReport.length() );
        OpenmrsUtil.copyFile(fis, response.getOutputStream());
        if(generatedReport.exists())
        generatedReport.delete();
        File f;
        if((f=new File(OpenmrsUtil.getApplicationDataDirectory()+hrReport.getFileName())).exists())
        f.delete();
		return SUCCESS_FORM_VIEW;
	}
	
}
