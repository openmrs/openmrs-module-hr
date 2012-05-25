package org.openmrs.module.hr.api.extension.html;

import org.openmrs.module.web.extension.LinkExt;

public class HumanResourcesGutterLinkExt extends LinkExt {

	@Override
	public String getLabel() {
		return "Manage Human Resources";
		
	}

	@Override
	public String getRequiredPrivilege() {
		return "Find Human Resources";
	}

	@Override
	public String getUrl() {
		return "module/hr/manager/findStaff.list";
	}

}
