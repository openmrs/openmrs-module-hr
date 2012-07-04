package org.openmrs.module.hr.web.servlet;


import org.openmrs.api.context.Context;
import org.openmrs.module.hr.HrIscoCodes;
import org.openmrs.module.hr.api.HRCompetencyService;
import org.openmrs.module.hr.api.HRPostService;
import org.openmrs.module.hr.api.HRQualificationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LevelPopulator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public LevelPopulator() {
    }
    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("certificateId") != null)
            returnCertificateLevels(request, response);
        else if(request.getParameter(("competencyId")) != null)
            returnCompetencyLevels(request, response);
    }

    private void returnCompetencyLevels (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String competencyId=request.getParameter("competencyId");
        HRCompetencyService hrCompetencyService = Context.getService(HRCompetencyService.class);
        String levels = hrCompetencyService.getCompetencyById(Integer.parseInt(competencyId)).getLevels();
        String competencyLevels[];
        String responseString = new String();
        if(levels.contains(",")){
            competencyLevels = levels.split(",");
            for(String level : competencyLevels)
                if(!responseString.isEmpty())
                    responseString = responseString + "^" + level ;
                else
                    responseString = responseString + level;
        }
        else {
            responseString = levels;
        }
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(new String(responseString));
    }

    private void returnCertificateLevels(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String certificateId=request.getParameter("certificateId");
        HRQualificationService hrQualificationService = Context.getService(HRQualificationService.class);
        String levels = hrQualificationService.getCertificateById(Integer.parseInt(certificateId)).getLevels();
        String certificateLevels[];
        String responseString = new String();
        if(levels.contains(",")){
            certificateLevels = levels.split(",");
            for(String level : certificateLevels)
                if(!responseString.isEmpty())
                    responseString = responseString + "^" + level ;
                else
                    responseString = responseString + level;
        }
        else {
            responseString = levels;
        }
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(new String(responseString));
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
