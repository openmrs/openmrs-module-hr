package org.openmrs.module.hr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.util.DatabaseUtil;
import org.openmrs.util.OpenmrsConstants;
import org.openmrs.util.OpenmrsUtil;

public class ReportGenerator {
	private static Log log = LogFactory.getLog(ReportGenerator.class);
	
	
	public static File generate(HrReport report,String outputFormat,String jrxmlUrl) throws IOException{
	
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
	URL resourceUrl = new URL(jrxmlUrl);
	InputStream is = resourceUrl.openStream(); 
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    FileWriter appDirJrxml=new FileWriter(new File(exportPath+report.getFileName()));
	BufferedWriter bw=new BufferedWriter(appDirJrxml);
	String line;
	while((line=br.readLine())!=null)
	{
		bw.write(line);
		bw.write("\n");
	}
	bw.flush();
	bw.close();
	JasperPrint jasperPrint = null;
	try {
		 JasperDesign jasperDesign = JRXmlLoader.load(exportPath+report.getFileName());
         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
         jasperPrint = JasperFillManager.fillReport(jasperReport,map, conn);
         if(outputFormat.equals("PDF")){
        	 File pdfFile=new File(exportPath+report.getName()+new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date())+".pdf");
        	 JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile.getAbsolutePath());
        	 return pdfFile;
         }
         else if(outputFormat.equals("Excel")){
        	 ByteArrayOutputStream output = new ByteArrayOutputStream();
        	 File outputfile=new File(exportPath+report.getName()+new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date())+".xls");
             OutputStream outputfileStream= new FileOutputStream(outputfile);
             JRXlsExporter exporterXLS = new JRXlsExporter(); 
        	 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
        	 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,output); 
        	 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
        	 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
        	 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
        	 exporterXLS.exportReport(); 
        	 outputfileStream.write(output.toByteArray()); 
        	 outputfileStream.flush();
        	 outputfileStream.close();
        	 return outputfile;
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
	}
	return null;
}
private static Connection connect(String url) throws SQLException {
	// Step 1: Load the JDBC driver.
	try {
		DatabaseUtil.loadDatabaseDriver(url);
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
