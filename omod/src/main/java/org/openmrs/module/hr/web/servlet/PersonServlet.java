package org.openmrs.module.hr.web.servlet;

import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        PersonService personService = Context.getService(PersonService.class);
        String responseString=personService.getPerson(Integer.parseInt(id)).getUuid();
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(new String(responseString));
    }
}
