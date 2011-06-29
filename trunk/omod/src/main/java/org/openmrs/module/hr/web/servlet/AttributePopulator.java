package org.openmrs.module.hr.web.servlet;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HRService;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.util.OpenmrsUtil;

/**
 * Servlet implementation class AttributePopulator
 */
public class AttributePopulator extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AttributePopulator() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		HRService hrService=Context.getService(HRService.class);
		HrIscoCodes iscoCode=hrService.getIscoCodeById(code);
		String responseString=iscoCode.getTitle()+"^"+iscoCode.getDefinition()+"^"+iscoCode.getTasksInclude()+"^"+iscoCode.getIncludedOccupations()+"^"+iscoCode.getExcludedOccupations();
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(new String(responseString));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
