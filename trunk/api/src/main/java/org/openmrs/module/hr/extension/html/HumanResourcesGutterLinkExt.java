package org.openmrs.module.hr.extension.html;

import org.openmrs.module.web.extension.LinkExt;

public class HumanResourcesGutterLinkExt extends LinkExt {

	@Override
	public String getLabel() {
		return "Human Resource";
		
	}

	@Override
	public String getRequiredPrivilege() {
		return null;
	}

	@Override
	public String getUrl() {
		return "module/hr/gutterIndexPage.form";
	}

}
