package org.openmrs.module.hr.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;*/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrReport;
import org.openmrs.module.hr.HrReportParameter;
import org.openmrs.propertyeditor.ConceptEditor;
import org.openmrs.propertyeditor.LocationEditor;
import org.openmrs.util.OpenmrsConstants;
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
	public String onSubmit(HttpServletRequest request,@ModelAttribute("HrReport") HrReport report,BindingResult errors) throws IOException
	{
		/*
		String outputFormat=request.getParameter("outputFormat");
		String url = Context.getRuntimeProperties().getProperty("connection.url", null);
		Connection conn;
		try {
			conn = connect(url);
		} catch (SQLException e) {
			log.error("Error connecting to DB.", e);
			return null;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		for(HrReportParameter reportParameter:report.getParameters()){
			if(reportParameter!=null)
			map.put(reportParameter.getName(),reportParameter.getValue());
		}
		log.debug("Report parameter map: " + map);
		String exportPath=OpenmrsUtil.getApplicationDataDirectory();
		JasperPrint jasperPrint = null;
		try {
			 JasperDesign jasperDesign = JRXmlLoader.load(request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())+"moduleResources/hr/jrxmls/"+report.getFileName());
	         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	         jasperPrint = JasperFillManager.fillReport(jasperReport,map, conn);
	         if(outputFormat.equals("PDF"))
	         JasperExportManager.exportReportToPdfFile(jasperPrint, exportPath+report.getName()+new SimpleDateFormat("dd-MM-yyyy-HH:mm").format(new Date())+".pdf");
	         else if(outputFormat.equals("Excel")){
	        	 ByteArrayOutputStream output = new ByteArrayOutputStream();
	             OutputStream outputfile= new FileOutputStream(new File(exportPath+report.getName()+new SimpleDateFormat("dd-MM-yyyy-HH:mm").format(new Date())+".xls"));
	             JRXlsExporter exporterXLS = new JRXlsExporter(); 
	        	 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
	        	 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,output); 
	        	 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
	        	 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
	        	 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
	        	 exporterXLS.exportReport(); 
	        	 outputfile.write(output.toByteArray()); 

	         }
		} catch (JRException e) {
			log.error("Error generating report", e);
		} finally{
			try {
				if (!conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				log.error("Exception closing report connection.", e);
			}
		}*/
		return SUCCESS_LIST_VIEW;
	}
	private static Connection connect(String url) throws SQLException {
		// Step 1: Load the JDBC driver.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error("Could not find JDBC driver class.", e);
			throw (SQLException) e.fillInStackTrace();
		}

		// Step 2: Establish the connection to the database.
		String username = Context.getRuntimeProperties().getProperty(
		"connection.username");
		String password = Context.getRuntimeProperties().getProperty(
		"connection.password");
		log.debug("connecting to DATABASE: " + OpenmrsConstants.DATABASE_NAME
				+ " USERNAME: " + username + " URL: " + url);
		return DriverManager.getConnection(url, username, password);
	}
}
