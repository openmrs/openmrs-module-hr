/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.module.Activator;
import org.openmrs.util.OpenmrsConstants;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class HumanResourceModuleActivator implements Activator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		Role humanResourceManager=null;
		Role humanResourceClerk=null;
		Set<Role> inheritedRoleSet=new HashSet<Role>();
		inheritedRoleSet.add(Context.getUserService().getRole(OpenmrsConstants.AUTHENTICATED_ROLE));
		List<Role> AllRoles=Context.getUserService().getAllRoles();
		for(Role r:AllRoles)
		{
			if(r.getName().equals("Human Resource Manager"))
				humanResourceManager=r;
			if(r.getName().equals("Human Resource Clerk"))
			humanResourceClerk=r;
		}
		if(humanResourceManager==null){
			humanResourceManager=new Role("Human Resource Manager","HR manager");
			humanResourceManager.setInheritedRoles(inheritedRoleSet);
			Context.getUserService().saveRole(humanResourceManager);
		}
		if(humanResourceClerk==null){
			humanResourceClerk=new Role("Human Resource Clerk","HR Clerk");
			humanResourceClerk.setInheritedRoles(inheritedRoleSet);
			Context.getUserService().saveRole(humanResourceClerk);
		}
		log.info("Starting Human Resource Module");
	}
	
	/**
	 * @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		Role humanResourceManager=null;
		Role humanResourceClerk=null;
		Set<Role> inheritedRoleSet=new HashSet<Role>();
		inheritedRoleSet.add(Context.getUserService().getRole(OpenmrsConstants.AUTHENTICATED_ROLE));
		List<Role> AllRoles=Context.getUserService().getAllRoles();
		for(Role r:AllRoles)
		{
			if(r.getName().equals("Human Resource Manager"))
				humanResourceManager=r;
			if(r.getName().equals("Human Resource Clerk"));
			humanResourceClerk=r;
		}
		if(humanResourceManager!=null){
			Context.getUserService().purgeRole(humanResourceManager);
		}
		if(humanResourceClerk!=null){
			Context.getUserService().purgeRole(humanResourceClerk);
		}
		log.info("Shutting down Human Resource Module");
	}
	
}

