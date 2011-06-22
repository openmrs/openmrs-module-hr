package org.openmrs.module.hr.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GutterIndexFormController {
	
	
		
		/** Logger for this class and subclasses */
		protected final Log log = LogFactory.getLog(getClass());
		
		/** Success form view name */
		
		private final String SUCCESS_FORM_VIEW = "/module/hr/gutterIndex";
		/**
		 * Initially called after the formBackingObject method to get the landing form name  
		 * @return String form view name
		 */
		@RequestMapping(value = "module/hr/gutterIndexPage.form")
		public String showPage(){
			return SUCCESS_FORM_VIEW;
		}
		
}
